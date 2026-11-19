package cl.duoc.backend_tienda.controllers;

import cl.duoc.backend_tienda.dtos.CompraDTO;
import cl.duoc.backend_tienda.dtos.TokenWebpay;
import cl.duoc.backend_tienda.services.WebpayService;
import cl.transbank.webpay.webpayplus.responses.WebpayPlusTransactionCommitResponse;
import cl.transbank.webpay.webpayplus.responses.WebpayPlusTransactionCreateResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/compras")
public class ComprasController {
  private final WebpayService webpayService;

  public ComprasController(WebpayService webpayService) {
    this.webpayService = webpayService;
  }

  @PostMapping("/generar-link-pago")
  public WebpayPlusTransactionCreateResponse iniciar(@RequestBody CompraDTO compra) throws Exception {
    return webpayService.iniciar(compra);
  }

  @PostMapping("/confirmar-pago")
  public WebpayPlusTransactionCommitResponse confirmar(@RequestBody TokenWebpay tokenData) throws Exception {
    return webpayService.confirmar(tokenData.getTokenWebpay());
  }
}
