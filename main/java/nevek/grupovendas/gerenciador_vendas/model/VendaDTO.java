package nevek.grupovendas.gerenciador_vendas.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public class VendaDTO {

    private Long id;
    private Double totalVenda;
    private Double totalPago;
    private Double desconto;
    private LocalDateTime dataHoraCriacao;
    private LocalDateTime ultimaAtualizacao;
    private UUID cliente;
    private UUID usuario;
    private List<UUID> produto;

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

    public UUID getCliente() {
        return cliente;
    }

    public void setCliente(final UUID cliente) {
        this.cliente = cliente;
    }

    public UUID getUsuario() {
        return usuario;
    }

    public void setUsuario(final UUID usuario) {
        this.usuario = usuario;
    }

    public List<UUID> getProduto() {
        return produto;
    }

    public void setProduto(final List<UUID> produto) {
        this.produto = produto;
    }

}
