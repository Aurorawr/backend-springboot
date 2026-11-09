package cl.duoc.backend_tienda.config;

import cl.duoc.backend_tienda.models.User;
import cl.duoc.backend_tienda.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

  private final UserRepository userRepository;

  public ApplicationConfig(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> {
      User usuario = userRepository.obtenerUsuarioPorCorreo(username);

      if (usuario == null) {
        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
      }

      return org.springframework.security.core.userdetails.User.builder()
          .username(usuario.getEmail())
          .password(usuario.getPassword())
          .roles(usuario.getRol().name())
          // Si no manejas roles aún, puedes usar: .authorities(Collections.emptyList())
          .build();
    };
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
