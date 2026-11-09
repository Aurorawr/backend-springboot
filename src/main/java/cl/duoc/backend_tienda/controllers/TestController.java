package cl.duoc.backend_tienda.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

  @GetMapping
  public ResponseEntity<String> secureEndpoint(Authentication authentication) {
    return ResponseEntity.ok("Acceso autorizado para el usuario: " + authentication.getName());
  }
}
