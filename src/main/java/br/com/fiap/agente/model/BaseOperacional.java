package br.com.fiap.agente.model;

import br.com.fiap.agente.controller.BaseOperacionalController;
import jakarta.persistence.*;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
public class BaseOperacional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String localizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public EntityModel<BaseOperacional> toEntityModel() {
        var linkSelf = linkTo(methodOn(BaseOperacionalController.class).findById(id)).withSelfRel().withTitle("Detalhes da base");
        var linkAll = linkTo(methodOn(BaseOperacionalController.class).findAllList()).withRel("all-bases").withTitle("Todas as bases operacionais");

        return EntityModel.of(this, linkSelf, linkAll);
    }
}