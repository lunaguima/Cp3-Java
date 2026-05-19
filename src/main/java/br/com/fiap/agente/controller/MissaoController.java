package br.com.fiap.agente.controller;

import br.com.fiap.agente.model.Missao;
import br.com.fiap.agente.service.MissaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missoes")
public class MissaoController {

    private final MissaoService service;

    public MissaoController(MissaoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<EntityModel<Missao>> findAll(Pageable pageable) {
        return service.findAll(pageable).map(Missao::toEntityModel);
    }

    @GetMapping("/todas")
    public List<EntityModel<Missao>> findAllList() {
        return service.findAllRaw()
                .stream()
                .map(Missao::toEntityModel)
                .toList();
    }

    @GetMapping("/{id}")
    public EntityModel<Missao> findById(@PathVariable Long id) {
        return service.findById(id).toEntityModel();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Missao> save(@RequestBody Missao missao) {
        return service.save(missao).toEntityModel();
    }

    @PutMapping("/{id}")
    public EntityModel<Missao> update(@PathVariable Long id, @RequestBody Missao missao) {
        return service.update(id, missao).toEntityModel();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}