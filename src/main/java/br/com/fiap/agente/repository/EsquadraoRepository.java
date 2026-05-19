package br.com.fiap.agente.repository;

import br.com.fiap.agente.model.Esquadrao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsquadraoRepository extends JpaRepository<Esquadrao, Long> {
}