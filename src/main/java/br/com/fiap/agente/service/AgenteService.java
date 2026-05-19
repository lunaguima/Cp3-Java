package br.com.fiap.agente.service;

import br.com.fiap.agente.model.Agente;
import br.com.fiap.agente.repository.AgenteRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AgenteService {

    private final AgenteRepository repository;

    public AgenteService(AgenteRepository repository) {
        this.repository = repository;
    }

    @Cacheable("agentes")
    public Page<Agente> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Agente> findAllRaw() {
        return repository.findAll();
    }

    @Cacheable(value = "agente", key = "#id")
    public Agente findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agente não encontrado"));
    }

    public Agente save(Agente agente) {
        return repository.save(agente);
    }

    public Agente update(Long id, Agente agenteAtualizado) {
        Agente agente = findById(id);
        agente.setNomeCodigo(agenteAtualizado.getNomeCodigo());
        agente.setCargo(agenteAtualizado.getCargo());
        agente.setEsquadrao(agenteAtualizado.getEsquadrao());
        agente.setMissoes(agenteAtualizado.getMissoes());
        return repository.save(agente);
    }

    public void delete(Long id) {
        Agente agente = findById(id);
        repository.delete(agente);
    }
}