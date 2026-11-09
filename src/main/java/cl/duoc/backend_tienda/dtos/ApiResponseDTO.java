package cl.duoc.backend_tienda.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseDTO<T> {
  private String mensaje;
  private T datos;
}
