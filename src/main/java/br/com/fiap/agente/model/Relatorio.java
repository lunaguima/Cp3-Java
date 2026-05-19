package br.com.fiap.agente.model;

import br.com.fiap.agente.controller.RelatorioController;
import jakarta.persistence.*;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String resumoOperacao;

    private String dataConclusao;

    @OneToOne
    private Missao missao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResumoOperacao() {
        return resumoOperacao;
    }

    public void setResumoOperacao(String resumoOperacao) {
        this.resumoOperacao = resumoOperacao;
    }

    public String getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Missao getMissao() {
        return missao;
    }

    public void setMissao(Missao missao) {
        this.missao = missao;
    }

    public EntityModel<Relatorio> toEntityModel() {
        var linkSelf = linkTo(methodOn(RelatorioController.class).findById(id)).withSelfRel().withTitle("Detalhes do relatório");
        var linkAll = linkTo(methodOn(RelatorioController.class).findAllList()).withRel("all-relatorios").withTitle("Todos os relatórios");

        return EntityModel.of(this, linkSelf, linkAll);
    }
}