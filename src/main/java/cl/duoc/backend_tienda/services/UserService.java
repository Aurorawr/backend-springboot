package cl.duoc.backend_tienda.services;

import cl.duoc.backend_tienda.dtos.UserDTO;
import cl.duoc.backend_tienda.exceptions.UsuarioNoEncontradoException;
import cl.duoc.backend_tienda.models.User;
import cl.duoc.backend_tienda.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  AuthenticationManager authenticationManager;

  public UserService(
      UserRepository userRepository,
      JwtService jwtService,
      UserDetailsService userDetailsService,
      AuthenticationManager authenticationManager
  ) {
    this.userRepository = userRepository;
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
    this.authenticationManager = authenticationManager;
  }

  public UserDTO iniciarSesion(String correo, String contraseña)
      throws UsuarioNoEncontradoException {
    User usuario = userRepository.obtenerUsuarioPorCorreoYContrasena(correo, contraseña);
    if (usuario == null) {
      throw new UsuarioNoEncontradoException("Credenciales inválidas");
    }
    UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getEmail());
    String token = jwtService.generateToken(userDetails);
    return UserDTO.builder()
        .id(usuario.getId())
        .email(usuario.getEmail())
        .nombre(usuario.getNombre())
        .apellido(usuario.getApellido())
        .rol(usuario.getRol())
        .token(token)
        .build();
  }
}
