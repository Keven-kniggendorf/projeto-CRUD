package nevek.grupovendas.gerenciador_vendas.model;


import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.hibernate.annotations.BatchSize;

import java.util.UUID;


public class CategoriaDTO {

    private UUID id;

    @NotNull
    private String nome;


    private String descricao;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

}
