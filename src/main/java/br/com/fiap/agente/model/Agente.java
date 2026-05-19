package br.com.fiap.agente.model;

import br.com.fiap.agente.controller.AgenteController;
import jakarta.persistence.*;
import org.springframework.hateoas.EntityModel;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
public class Agente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCodigo;

    @ManyToOne
    private Cargo cargo;

    @ManyToOne
    private Esquadrao esquadrao;

    @ManyToMany
    private List<Missao> missoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCodigo() {
        return nomeCodigo;
    }

    public void setNomeCodigo(String nomeCodigo) {
        this.nomeCodigo = nomeCodigo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Esquadrao getEsquadrao() {
        return esquadrao;
    }

    public void setEsquadrao(Esquadrao esquadrao) {
        this.esquadrao = esquadrao;
    }

    public List<Missao> getMissoes() {
        return missoes;
    }

    public void setMissoes(List<Missao> missoes) {
        this.missoes = missoes;
    }

    public EntityModel<Agente> toEntityModel() {
        var linkSelf = linkTo(methodOn(AgenteController.class).findById(id)).withSelfRel().withTitle("Detalhes do agente");
        var linkAll = linkTo(methodOn(AgenteController.class).findAllList()).withRel("all-agentes").withTitle("Todos os agentes");

        return EntityModel.of(this, linkSelf, linkAll);
    }
}