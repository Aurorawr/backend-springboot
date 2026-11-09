package cl.duoc.backend_tienda.dtos;

import cl.duoc.backend_tienda.enums.Rol;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
  private int id;
  private String email;
  private String nombre;
  private String apellido;
  private Rol rol;
  private String token;
}
