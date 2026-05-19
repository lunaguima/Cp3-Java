package br.com.fiap.agente.controller;

import br.com.fiap.agente.model.Cargo;
import br.com.fiap.agente.service.CargoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    private final CargoService service;

    public CargoController(CargoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<EntityModel<Cargo>> findAll(Pageable pageable) {
        return service.findAll(pageable).map(Cargo::toEntityModel);
    }

    @GetMapping("/todos")
    public List<EntityModel<Cargo>> findAllList() {
        return service.findAllRaw()
                .stream()
                .map(Cargo::toEntityModel)
                .toList();
    }

    @GetMapping("/{id}")
    public EntityModel<Cargo> findById(@PathVariable Long id) {
        return service.findById(id).toEntityModel();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Cargo> save(@RequestBody Cargo cargo) {
        return service.save(cargo).toEntityModel();
    }

    @PutMapping("/{id}")
    public EntityModel<Cargo> update(@PathVariable Long id, @RequestBody Cargo cargo) {
        return service.update(id, cargo).toEntityModel();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}