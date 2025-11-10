package br.com.isaque.pagamentos.repository;

import br.com.isaque.pagamentos.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
    Optional<Transacao> findById(UUID id);
    List<Transacao> findAll();
}