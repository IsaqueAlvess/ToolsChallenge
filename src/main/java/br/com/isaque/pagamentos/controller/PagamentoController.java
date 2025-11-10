package br.com.isaque.pagamentos.controller;

import br.com.isaque.pagamentos.dto.PagamentoRequest;
import br.com.isaque.pagamentos.dto.PagamentoResponse;
import br.com.isaque.pagamentos.service.ConsultaService;
import br.com.isaque.pagamentos.service.EstornoService;
import br.com.isaque.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final EstornoService estornoService;
    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<PagamentoResponse> criarPagamento(@Valid @RequestBody PagamentoRequest request){
        PagamentoResponse response = pagamentoService.processarPagamento(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

   @PostMapping("/{id}/estorno")
    public ResponseEntity<PagamentoResponse> estornarPagamento(@PathVariable String id){
        PagamentoResponse response = estornoService.estornarPagamentoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
   }

   @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponse> consultarPorId(@PathVariable String id){
        PagamentoResponse response = consultaService.consultarPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
   }

   @GetMapping
    public ResponseEntity<List<PagamentoResponse>> consultarTodosPagamentos(){
        List<PagamentoResponse> listaPagamentos = consultaService.listarTodos();

        return ResponseEntity.status(HttpStatus.OK).body(listaPagamentos);
   }


}
