package br.com.fiap.agente.repository;

import br.com.fiap.agente.model.BaseOperacional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseOperacionalRepository extends JpaRepository<BaseOperacional, Long> {
}