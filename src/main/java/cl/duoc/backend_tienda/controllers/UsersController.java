package cl.duoc.backend_tienda.controllers;

import cl.duoc.backend_tienda.dtos.ApiResponseDTO;
import cl.duoc.backend_tienda.dtos.CredentialsDTO;
import cl.duoc.backend_tienda.dtos.UserDTO;
import cl.duoc.backend_tienda.exceptions.UsuarioNoEncontradoException;
import cl.duoc.backend_tienda.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UsersController {
  private final UserService userService;

  public UsersController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/iniciar-sesion")
  public ResponseEntity<ApiResponseDTO<UserDTO>> iniciarSesion(@RequestBody CredentialsDTO credenciales) {
    try {
      UserDTO usuario = userService.iniciarSesion(credenciales.getEmail(), credenciales.getPassword());

      return ResponseEntity.ok(ApiResponseDTO.<UserDTO>builder()
              .mensaje("Sesión iniciada con éxito")
              .datos(usuario)
          .build()
      );
    } catch (UsuarioNoEncontradoException e) {
      return new ResponseEntity<>(ApiResponseDTO.<UserDTO>builder()
          .mensaje(e.getMessage())
          .build(), HttpStatus.UNAUTHORIZED);
    }
  }
}
