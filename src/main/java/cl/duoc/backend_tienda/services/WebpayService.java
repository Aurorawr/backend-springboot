package cl.duoc.backend_tienda.services;

import cl.duoc.backend_tienda.dtos.CompraDTO;
import cl.transbank.common.IntegrationApiKeys;
import cl.transbank.common.IntegrationCommerceCodes;
import cl.transbank.common.IntegrationType;
import cl.transbank.webpay.common.WebpayOptions;
import cl.transbank.webpay.exception.TransactionCommitException;
import cl.transbank.webpay.webpayplus.WebpayPlus;
import cl.transbank.webpay.webpayplus.responses.WebpayPlusTransactionCommitResponse;
import cl.transbank.webpay.webpayplus.responses.WebpayPlusTransactionCreateResponse;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class WebpayService {
  private final WebpayPlus.Transaction tx;

  public WebpayService() {
    this.tx = new WebpayPlus.Transaction(new WebpayOptions(
        IntegrationCommerceCodes.WEBPAY_PLUS,
        IntegrationApiKeys.WEBPAY,
        IntegrationType.TEST
    ));
  }

  public WebpayPlusTransactionCreateResponse iniciar(CompraDTO compra) throws Exception {
    return tx.create(
        compra.getOrdenDeCompra(),
        compra.getIdSesion(),
        compra.getMonto(),
        compra.getUrlRetorno()
    );
  }

  public WebpayPlusTransactionCommitResponse confirmar(String tokenWs)
      throws TransactionCommitException, IOException {
    return tx.commit(tokenWs);
  }
}
