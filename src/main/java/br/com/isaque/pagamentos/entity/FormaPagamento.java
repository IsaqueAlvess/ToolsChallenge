package br.com.isaque.pagamentos.entity;

import br.com.isaque.pagamentos.entity.enums.TipoPagamento;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormaPagamento {

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipo;

    private int parcelas;
}
