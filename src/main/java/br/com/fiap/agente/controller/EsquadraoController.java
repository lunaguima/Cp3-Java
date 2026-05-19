package br.com.fiap.agente.controller;

import br.com.fiap.agente.model.Esquadrao;
import br.com.fiap.agente.service.EsquadraoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/esquadroes")
public class EsquadraoController {

    private final EsquadraoService service;

    public EsquadraoController(EsquadraoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<EntityModel<Esquadrao>> findAll(Pageable pageable) {
        return service.findAll(pageable).map(Esquadrao::toEntityModel);
    }

    @GetMapping("/todos")
    public List<EntityModel<Esquadrao>> findAllList() {
        return service.findAllRaw()
                .stream()
                .map(Esquadrao::toEntityModel)
                .toList();
    }

    @GetMapping("/{id}")
    public EntityModel<Esquadrao> findById(@PathVariable Long id) {
        return service.findById(id).toEntityModel();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Esquadrao> save(@RequestBody Esquadrao esquadrao) {
        return service.save(esquadrao).toEntityModel();
    }

    @PutMapping("/{id}")
    public EntityModel<Esquadrao> update(@PathVariable Long id, @RequestBody Esquadrao esquadrao) {
        return service.update(id, esquadrao).toEntityModel();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}