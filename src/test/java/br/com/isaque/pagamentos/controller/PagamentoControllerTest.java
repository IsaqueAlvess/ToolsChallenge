package br.com.isaque.pagamentos.controller;

import br.com.isaque.pagamentos.dto.PagamentoRequest;
import br.com.isaque.pagamentos.dto.PagamentoResponse;
import br.com.isaque.pagamentos.entity.Descricao;
import br.com.isaque.pagamentos.entity.FormaPagamento;
import br.com.isaque.pagamentos.entity.enums.StatusTransacao;
import br.com.isaque.pagamentos.entity.enums.TipoPagamento;
import br.com.isaque.pagamentos.service.ConsultaService;
import br.com.isaque.pagamentos.service.EstornoService;
import br.com.isaque.pagamentos.service.PagamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PagamentoController.class)
class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PagamentoService pagamentoService;

    @MockBean
    private EstornoService estornoService;

    @MockBean
    private ConsultaService consultaService;

    private PagamentoRequest pagamentoRequest;
    private PagamentoResponse pagamentoResponse;

    @BeforeEach
    void setup() {
        // --- Mock Descrição
        Descricao descricao = Descricao.builder()
                .valor(BigDecimal.valueOf(500.50))
                .dataHora(LocalDateTime.of(2021, 5, 1, 18, 30))
                .estabelecimento("PetShop Mundo cão")
                .nsu("1234567890")
                .codigoAutorizacao("147258369")
                .status(StatusTransacao.AUTORIZADO)
                .build();

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setTipo(TipoPagamento.AVISTA);
        formaPagamento.setParcelas(1);

        var transacaoRequest = new PagamentoRequest.TransacaoRequest(
                "100023568900001",
                "4444********1234",
                descricao,
                formaPagamento
        );
        pagamentoRequest = new PagamentoRequest(transacaoRequest);

        var transacaoResponse = new PagamentoResponse.TransacaoResponse(
                "4444********1234",
                "100023568900001",
                descricao,
                formaPagamento
        );
        pagamentoResponse = new PagamentoResponse(transacaoResponse);
    }

    @Test
    void deveCriarPagamentoComSucessoERetornar201() throws Exception {
        when(pagamentoService.processarPagamento(any(PagamentoRequest.class)))
                .thenReturn(pagamentoResponse);

        mockMvc.perform(post("/api/v1/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pagamentoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transacao.id").value("100023568900001"))
                .andExpect(jsonPath("$.transacao.descricao.status").value("AUTORIZADO"));
    }

    @Test
    void deveListarTodosPagamentos() throws Exception {
        when(consultaService.listarTodos())
                .thenReturn(List.of(pagamentoResponse));

        mockMvc.perform(get("/api/v1/pagamentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transacao.id").value("100023568900001"))
                .andExpect(jsonPath("$[0].transacao.descricao.status").value("AUTORIZADO"));
    }

    @Test
    void deveConsultarPagamentoPorId() throws Exception {
        when(consultaService.consultarPorId("100023568900001"))
                .thenReturn(pagamentoResponse);

        mockMvc.perform(get("/api/v1/pagamentos/100023568900001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transacao.id").value("100023568900001"))
                .andExpect(jsonPath("$.transacao.descricao.status").value("AUTORIZADO"));
    }

    @Test
    void deveEstornarPagamentoComSucesso() throws Exception {
        var cancelado = pagamentoResponse;
        cancelado.transacao().descricao().setStatus(StatusTransacao.CANCELADO);

        when(estornoService.estornarPagamentoPorId("100023568900001"))
                .thenReturn(cancelado);

        mockMvc.perform(post("/api/v1/pagamentos/100023568900001/estorno"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transacao.id").value("100023568900001"))
                .andExpect(jsonPath("$.transacao.descricao.status").value("CANCELADO"));
    }
}
