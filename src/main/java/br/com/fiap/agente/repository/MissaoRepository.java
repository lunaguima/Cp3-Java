package br.com.fiap.agente.repository;

import br.com.fiap.agente.model.Missao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Long> {
}