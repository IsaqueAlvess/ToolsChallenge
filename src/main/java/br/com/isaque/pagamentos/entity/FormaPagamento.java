package br.com.isaque.pagamentos.entity;

import br.com.isaque.pagamentos.entity.enums.TipoPagamento;
import lombok.Data;

@Data
public class FormaPagamento {
    private int parcelas;
    private TipoPagamento tipo;
}
