package br.com.fiap.agente.controller;

import br.com.fiap.agente.model.Relatorio;
import br.com.fiap.agente.service.RelatorioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final RelatorioService service;

    public RelatorioController(RelatorioService service) {
        this.service = service;
    }

    @GetMapping
    public Page<EntityModel<Relatorio>> findAll(Pageable pageable) {
        return service.findAll(pageable).map(Relatorio::toEntityModel);
    }

    @GetMapping("/todos")
    public List<EntityModel<Relatorio>> findAllList() {
        return service.findAllRaw()
                .stream()
                .map(Relatorio::toEntityModel)
                .toList();
    }

    @GetMapping("/{id}")
    public EntityModel<Relatorio> findById(@PathVariable Long id) {
        return service.findById(id).toEntityModel();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Relatorio> save(@RequestBody Relatorio relatorio) {
        return service.save(relatorio).toEntityModel();
    }

    @PutMapping("/{id}")
    public EntityModel<Relatorio> update(@PathVariable Long id, @RequestBody Relatorio relatorio) {
        return service.update(id, relatorio).toEntityModel();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}