package br.com.isaque.pagamentos.service;

import br.com.isaque.pagamentos.dto.PagamentoResponse;
import br.com.isaque.pagamentos.entity.Transacao;
import br.com.isaque.pagamentos.repository.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
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
        return transacaoRepository.findById(id)
                .map(transacao -> new PagamentoResponse(
                        new PagamentoResponse.TransacaoResponse(
                                transacao.getCartao(),
                                transacao.getId(),
                                transacao.getDescricao(),
                                transacao.getFormaPagamento()
                        )
                ))
                .orElseThrow(() -> new EntityNotFoundException("Transação não encontrada para o ID: " + id));
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
