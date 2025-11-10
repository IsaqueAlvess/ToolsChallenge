package br.com.isaque.pagamentos.service;

import br.com.isaque.pagamentos.dto.PagamentoRequest;
import br.com.isaque.pagamentos.dto.PagamentoResponse;
import br.com.isaque.pagamentos.entity.Descricao;
import br.com.isaque.pagamentos.entity.enums.StatusTransacao;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PagamentoService {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final char[] DIGITOS = "0123456789".toCharArray();

    public PagamentoResponse processarPagamento(PagamentoRequest request) {

        PagamentoRequest.TransacaoRequest transacaoRequest = request.transacao();

        Descricao descricaoPagamento = request.transacao().descricao();
        descricaoPagamento.setNsu(gerarNumeroRandom(10));
        descricaoPagamento.setCodigoAutorizacao(gerarNumeroRandom(9));
        descricaoPagamento.setStatus(StatusTransacao.AUTORIZADO);

        //Response - Devolve o objeto JSON
        PagamentoResponse.TransacaoResponse transacaoResponse =
                new PagamentoResponse.TransacaoResponse(
                        transacaoRequest.id(),
                        transacaoRequest.cartao(),
                        descricaoPagamento,
                        transacaoRequest.formaPagamento()
                );

        return new PagamentoResponse(transacaoResponse);
    }

    private String gerarNumeroRandom(int tamanho) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            sb.append(DIGITOS[SECURE_RANDOM.nextInt(DIGITOS.length)]);
        }

        return sb.toString();
    }
}