package br.com.isaque.pagamentos.service;

import br.com.isaque.pagamentos.dto.PagamentoResponse;
import br.com.isaque.pagamentos.entity.Transacao;
import br.com.isaque.pagamentos.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final TransacaoRepository transacaoRepository;

    public PagamentoResponse consultarPorId(String id){
        Optional<Transacao> transacao = transacaoRepository.findById(id);

        PagamentoResponse.TransacaoResponse response = new PagamentoResponse.TransacaoResponse(
                transacao.get().getCartao(),
                transacao.get().getId(),
                transacao.get().getDescricao(),
                transacao.get().getFormaPagamento()
        );

        return new PagamentoResponse(response);

    }

    public List<PagamentoResponse> listarTodos(){
        return transacaoRepository.findAll().stream()
                .map(transacao -> new PagamentoResponse(
                            new PagamentoResponse.TransacaoResponse(
                                    transacao.getCartao(),
                                    transacao.getId(),
                                    transacao.getDescricao(),
                                    transacao.getFormaPagamento()
                            )
                    )).collect(Collectors.toList());
    }

}
