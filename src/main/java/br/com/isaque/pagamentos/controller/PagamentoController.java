package br.com.isaque.pagamentos.controller;

import br.com.isaque.pagamentos.dto.PagamentoRequest;
import br.com.isaque.pagamentos.dto.PagamentoResponse;
import br.com.isaque.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @GetMapping
    public String pagamentoController() {
        return "Hello";
    }

    @PostMapping
    public ResponseEntity<PagamentoResponse> criarPagamento(@Valid @RequestBody PagamentoRequest request){
        PagamentoResponse response = pagamentoService.processarPagamento(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
