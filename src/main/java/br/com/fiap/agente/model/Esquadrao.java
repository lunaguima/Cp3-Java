package br.com.fiap.agente.model;

import br.com.fiap.agente.controller.EsquadraoController;
import jakarta.persistence.*;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
public class Esquadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codinome;

    @ManyToOne
    private BaseOperacional baseOperacional;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodinome() {
        return codinome;
    }

    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }

    public BaseOperacional getBaseOperacional() {
        return baseOperacional;
    }

    public void setBaseOperacional(BaseOperacional baseOperacional) {
        this.baseOperacional = baseOperacional;
    }

    public EntityModel<Esquadrao> toEntityModel() {
        var linkSelf = linkTo(methodOn(EsquadraoController.class).findById(id)).withSelfRel().withTitle("Detalhes do esquadrão");
        var linkAll = linkTo(methodOn(EsquadraoController.class).findAllList()).withRel("all-esquadroes").withTitle("Todos os esquadrões");

        return EntityModel.of(this, linkSelf, linkAll);
    }
}