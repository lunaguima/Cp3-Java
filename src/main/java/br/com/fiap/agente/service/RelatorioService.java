package br.com.fiap.agente.service;

import br.com.fiap.agente.model.Relatorio;
import br.com.fiap.agente.repository.RelatorioRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RelatorioService {

    private final RelatorioRepository repository;

    public RelatorioService(RelatorioRepository repository) {
        this.repository = repository;
    }

    @Cacheable("relatorios")
    public Page<Relatorio> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Relatorio> findAllRaw() {
        return repository.findAll();
    }

    @Cacheable(value = "relatorio", key = "#id")
    public Relatorio findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Relatório não encontrado"));
    }

    public Relatorio save(Relatorio relatorio) {
        return repository.save(relatorio);
    }

    public Relatorio update(Long id, Relatorio relatorioAtualizado) {
        Relatorio relatorio = findById(id);
        relatorio.setResumoOperacao(relatorioAtualizado.getResumoOperacao());
        relatorio.setDataConclusao(relatorioAtualizado.getDataConclusao());
        relatorio.setMissao(relatorioAtualizado.getMissao());
        return repository.save(relatorio);
    }

    public void delete(Long id) {
        Relatorio relatorio = findById(id);
        repository.delete(relatorio);
    }
}