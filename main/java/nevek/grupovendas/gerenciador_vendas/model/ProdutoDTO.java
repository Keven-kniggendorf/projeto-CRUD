package nevek.grupovendas.gerenciador_vendas.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


public class ProdutoDTO {

    private UUID id;

    @NotNull
    @Size(max = 255)
    private String nome;

    @Size(max = 255)
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @NotNull
    private Integer quantidade;

    @NotNull
    private String categorias;


    private UUID usuario;

    private UUID categoria;

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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(final BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(final Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(final String categorias) {
        this.categorias = categorias;
    }

    public UUID getUsuario() {
        return usuario;
    }

    public void setUsuario(final UUID usuario) {
        this.usuario = usuario;
    }

    public UUID getCategoria() {
        return categoria;
    }

    public void setCategoria(final UUID categoria) {
        this.categoria = categoria;
    }

}
