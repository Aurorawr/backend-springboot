package cl.duoc.backend_tienda.repositories;

import cl.duoc.backend_tienda.enums.Rol;
import cl.duoc.backend_tienda.models.User;
import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  private List<User> usuarios = new ArrayList<>(List.of(
      User.builder()
          .id(1)
          .email("luis@example.com")
          .password("Luis1234")
          .nombre("Luis")
          .apellido("Quintana")
          .rol(Rol.ADMIN)
          .build()
  ));

  @Nullable
  public User obtenerUsuarioPorCorreoYContrasena(String correo, String contraseña) {
    return usuarios.stream()
        .filter(u -> u.getEmail().equals(correo) && u.getPassword().equals(contraseña))
        .findFirst()
        .orElse(null);
  }

  @Nullable
  public User obtenerUsuarioPorCorreo(String correo) {
    return usuarios.stream()
        .filter(u -> u.getEmail().equals(correo))
        .findFirst()
        .orElse(null);
  }
}
