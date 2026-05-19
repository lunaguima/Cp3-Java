package br.com.fiap.agente.service;

import br.com.fiap.agente.model.Esquadrao;
import br.com.fiap.agente.repository.EsquadraoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EsquadraoService {

    private final EsquadraoRepository repository;

    public EsquadraoService(EsquadraoRepository repository) {
        this.repository = repository;
    }

    @Cacheable("esquadroes")
    public Page<Esquadrao> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Esquadrao> findAllRaw() {
        return repository.findAll();
    }

    @Cacheable(value = "esquadrao", key = "#id")
    public Esquadrao findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esquadrão não encontrado"));
    }

    public Esquadrao save(Esquadrao esquadrao) {
        return repository.save(esquadrao);
    }

    public Esquadrao update(Long id, Esquadrao esquadraoAtualizado) {
        Esquadrao esquadrao = findById(id);
        esquadrao.setCodinome(esquadraoAtualizado.getCodinome());
        esquadrao.setBaseOperacional(esquadraoAtualizado.getBaseOperacional());
        return repository.save(esquadrao);
    }

    public void delete(Long id) {
        Esquadrao esquadrao = findById(id);
        repository.delete(esquadrao);
    }
}