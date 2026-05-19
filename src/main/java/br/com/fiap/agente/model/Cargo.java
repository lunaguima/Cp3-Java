package br.com.fiap.agente.model;

import br.com.fiap.agente.controller.CargoController;
import jakarta.persistence.*;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String nivelAutorizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNivelAutorizacao() {
        return nivelAutorizacao;
    }

    public void setNivelAutorizacao(String nivelAutorizacao) {
        this.nivelAutorizacao = nivelAutorizacao;
    }

    public EntityModel<Cargo> toEntityModel() {
        var linkSelf = linkTo(methodOn(CargoController.class).findById(id)).withSelfRel().withTitle("Detalhes do cargo");
        var linkAll = linkTo(methodOn(CargoController.class).findAllList()).withRel("all-cargos").withTitle("Todos os cargos");

        return EntityModel.of(this, linkSelf, linkAll);
    }
}