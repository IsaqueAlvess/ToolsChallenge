package br.com.isaque.pagamentos.service;

import br.com.isaque.pagamentos.dto.PagamentoResponse;
import br.com.isaque.pagamentos.entity.Transacao;
import br.com.isaque.pagamentos.entity.enums.StatusTransacao;
import br.com.isaque.pagamentos.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstornoService {

    private final TransacaoRepository transacaoRepository;

    public PagamentoResponse estornarPagamentoPorId(String id) {

        Transacao transacao = transacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Transação não encontrada com o id: " + id));

        if(transacao.getDescricao().getStatus() != StatusTransacao.AUTORIZADO){
            throw new RuntimeException("Transação não pode ser estornada, pois não foi AUTORIZADA!");
        }

        transacao.getDescricao().setStatus(StatusTransacao.CANCELADO);
        transacaoRepository.save(transacao);

        PagamentoResponse.TransacaoResponse response = new PagamentoResponse.TransacaoResponse(
                transacao.getCartao(),
                transacao.getId(),
                transacao.getDescricao(),
                transacao.getFormaPagamento()
        );

        return new PagamentoResponse(response);
    }
}
