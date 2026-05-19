package br.com.fiap.agente.repository;

import br.com.fiap.agente.model.Agente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, Long> {
}