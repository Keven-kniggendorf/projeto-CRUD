package nevek.grupovendas.gerenciador_vendas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Venda {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double totalVenda;

    @Column
    private Double totalPago;

    @Column
    private Double desconto;

    @Column
    private LocalDateTime dataHoraCriacao;

    @Column
    private LocalDateTime ultimaAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "venda_produto",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;


//    @ManyToMany
//    @JoinTable(
//            name = "VendaItem",
//            joinColumns = @JoinColumn(name = "vendaId"),
//            inverseJoinColumns = @JoinColumn(name = "produtoId")
//    )
//    private Set<Produto> produto;

    public Venda() {
        this.dataHoraCriacao = LocalDateTime.now();
    }

    public Venda(final Double totalVenda, final Double totalPago, final Double desconto) {
        this();
        this.totalVenda = totalVenda;
        this.totalPago = totalPago;
        this.desconto = desconto;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(final Double totalVenda) {
        this.totalVenda = totalVenda;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(final Double totalPago) {
        this.totalPago = totalPago;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(final Double desconto) {
        this.desconto = desconto;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(final LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(final LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(final Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(final Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProduto(final List<Produto> produto) {
        this.produtos = produtos;
    }



}
