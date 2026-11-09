package cl.duoc.backend_tienda.models;

import cl.duoc.backend_tienda.enums.Rol;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
  private int id;
  private String email;
  private String password;
  private String nombre;
  private String apellido;
  private Rol rol;
}
