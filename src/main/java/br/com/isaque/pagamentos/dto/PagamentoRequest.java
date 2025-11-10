package br.com.isaque.pagamentos.dto;

import br.com.isaque.pagamentos.entity.Descricao;
import br.com.isaque.pagamentos.entity.FormaPagamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PagamentoRequest(

    @NotNull
    @Valid
    TransacaoRequest transacao
) {

    public record TransacaoRequest(
        @NotNull
        @Valid
        String id,

        @NotNull
        @Valid
        String cartao,

        @NotNull
        @Valid
        Descricao descricao,

        @NotNull
        @Valid
        FormaPagamento formaPagamento
    ) {}

}
