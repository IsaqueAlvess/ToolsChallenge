package br.com.isaque.pagamentos.dto;

import br.com.isaque.pagamentos.entity.Descricao;
import br.com.isaque.pagamentos.entity.FormaPagamento;

import java.util.UUID;

public record PagamentoResponse(
    TransacaoResponse transacao
){
    public record TransacaoResponse(
            String cartao,
            String id,
            Descricao descricao,
            FormaPagamento formaPagamento
    ) {}

}
