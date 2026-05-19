package br.com.fiap.agente.service;

import br.com.fiap.agente.model.Missao;
import br.com.fiap.agente.repository.MissaoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MissaoService {

    private final MissaoRepository repository;

    public MissaoService(MissaoRepository repository) {
        this.repository = repository;
    }

    @Cacheable("missoes")
    public Page<Missao> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Missao> findAllRaw() {
        return repository.findAll();
    }

    @Cacheable(value = "missao", key = "#id")
    public Missao findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Missão não encontrada"));
    }

    public Missao save(Missao missao) {
        return repository.save(missao);
    }

    public Missao update(Long id, Missao missaoAtualizada) {
        Missao missao = findById(id);
        missao.setObjetivo(missaoAtualizada.getObjetivo());
        missao.setStatus(missaoAtualizada.getStatus());
        return repository.save(missao);
    }

    public void delete(Long id) {
        Missao missao = findById(id);
        repository.delete(missao);
    }
}