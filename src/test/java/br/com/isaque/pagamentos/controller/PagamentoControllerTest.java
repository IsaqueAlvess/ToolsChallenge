package br.com.isaque.pagamentos.controller;

import br.com.isaque.pagamentos.dto.PagamentoRequest;
import br.com.isaque.pagamentos.dto.PagamentoResponse;
import br.com.isaque.pagamentos.entity.Descricao;
import br.com.isaque.pagamentos.entity.FormaPagamento;
import br.com.isaque.pagamentos.entity.enums.StatusTransacao;
import br.com.isaque.pagamentos.entity.enums.TipoPagamento;
import br.com.isaque.pagamentos.service.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PagamentoControllerTest {

    @Mock
    private PagamentoService pagamentoService;

    @InjectMocks
    private PagamentoController pagamentoController;

    private PagamentoRequest pagamentoRequest;
    private PagamentoResponse pagamentoResponse;

    @BeforeEach
    void setup() {
        Descricao descricao = Descricao.builder()
                .valor(BigDecimal.valueOf(500.50))
                .dataHora(LocalDateTime.of(2021, 5, 1, 18, 30))
                .estabelecimento("PetShop Mundo cão")
                .build();

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setTipo(TipoPagamento.AVISTA);
        formaPagamento.setParcelas(1);

        PagamentoRequest.TransacaoRequest transacaoRequest =
                new PagamentoRequest.TransacaoRequest("100023568900001", "4444********1234", descricao, formaPagamento);
        pagamentoRequest = new PagamentoRequest(transacaoRequest);

        descricao.setNsu("1234567890");
        descricao.setCodigoAutorizacao("147258369");
        descricao.setStatus(StatusTransacao.AUTORIZADO);

        PagamentoResponse.TransacaoResponse transacaoResponse =
                new PagamentoResponse.TransacaoResponse(
                        "4444********1234",
                        "100023568900001",
                        descricao,
                        formaPagamento
                );
        pagamentoResponse = new PagamentoResponse(transacaoResponse);
    }

    @Test
    void deveCriarPagamentoComSucessoERetornar201() {
        when(pagamentoService.processarPagamento(any(PagamentoRequest.class))).thenReturn(pagamentoResponse);

        ResponseEntity<PagamentoResponse> response = pagamentoController.criarPagamento(pagamentoRequest);

        assertThat(response.getStatusCode().value()).isEqualTo(201);
        assertThat(response.getBody().transacao().id()).isEqualTo("100023568900001");
        assertThat(response.getBody().transacao().descricao().getStatus()).isEqualTo(StatusTransacao.AUTORIZADO);
    }
}