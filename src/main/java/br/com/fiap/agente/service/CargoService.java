package br.com.fiap.agente.service;

import br.com.fiap.agente.model.Cargo;
import br.com.fiap.agente.repository.CargoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CargoService {

    private final CargoRepository repository;

    public CargoService(CargoRepository repository) {
        this.repository = repository;
    }

    @Cacheable("cargos")
    public Page<Cargo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Cargo> findAllRaw() {
        return repository.findAll();
    }

    @Cacheable(value = "cargo", key = "#id")
    public Cargo findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cargo não encontrado"));
    }

    public Cargo save(Cargo cargo) {
        return repository.save(cargo);
    }

    public Cargo update(Long id, Cargo cargoAtualizado) {
        Cargo cargo = findById(id);
        cargo.setTitulo(cargoAtualizado.getTitulo());
        cargo.setNivelAutorizacao(cargoAtualizado.getNivelAutorizacao());
        return repository.save(cargo);
    }

    public void delete(Long id) {
        Cargo cargo = findById(id);
        repository.delete(cargo);
    }
}