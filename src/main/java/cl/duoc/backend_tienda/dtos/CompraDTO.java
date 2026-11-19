package cl.duoc.backend_tienda.dtos;

import lombok.Data;

@Data
public class CompraDTO {
  String ordenDeCompra;
  String idSesion;
  long monto;
  String urlRetorno;
}
