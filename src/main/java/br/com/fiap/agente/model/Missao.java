package br.com.fiap.agente.model;

import br.com.fiap.agente.controller.MissaoController;
import jakarta.persistence.*;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
public class Missao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String objetivo;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EntityModel<Missao> toEntityModel() {
        var linkSelf = linkTo(methodOn(MissaoController.class).findById(id)).withSelfRel().withTitle("Detalhes da missão");
        var linkAll = linkTo(methodOn(MissaoController.class).findAllList()).withRel("all-missoes").withTitle("Todas as missões");

        return EntityModel.of(this, linkSelf, linkAll);
    }
}