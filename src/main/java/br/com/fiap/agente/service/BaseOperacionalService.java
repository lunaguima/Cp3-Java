package br.com.fiap.agente.service;

import br.com.fiap.agente.model.BaseOperacional;
import br.com.fiap.agente.repository.BaseOperacionalRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BaseOperacionalService {

    private final BaseOperacionalRepository repository;

    public BaseOperacionalService(BaseOperacionalRepository repository) {
        this.repository = repository;
    }

    @Cacheable("bases")
    public Page<BaseOperacional> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<BaseOperacional> findAllRaw() {
        return repository.findAll();
    }

    @Cacheable(value = "base", key = "#id")
    public BaseOperacional findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Base não encontrada"));
    }

    public BaseOperacional save(BaseOperacional base) {
        return repository.save(base);
    }

    public BaseOperacional update(Long id, BaseOperacional baseAtualizada) {
        BaseOperacional base = findById(id);
        base.setNome(baseAtualizada.getNome());
        base.setLocalizacao(baseAtualizada.getLocalizacao());
        return repository.save(base);
    }

    public void delete(Long id) {
        BaseOperacional base = findById(id);
        repository.delete(base);
    }
}