package nevek.grupovendas.gerenciador_vendas.domain;

import jakarta.persistence.*;
import nevek.grupovendas.gerenciador_vendas.model.StatusAgendamento;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Agendamento {

    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "char(36)")
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAgendamento status;

    @ManyToOne
    private Usuario barbeiro; // ou Barbeiro


    @ManyToOne
    Cliente cliente;

    @ManyToOne
    Produto produto;

    // @OneToMany(mappedBy = "agendamento")
    //private List<ItemAgendamento> itens;  // Itens de agendamento

    public Agendamento() {
    }

    public Agendamento(final LocalDateTime dataHora, final BigDecimal valorTotal, final String status) {
        this.dataHora = dataHora;
        this.valorTotal = valorTotal;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(final LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(final BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(final StatusAgendamento status) {
        this.status = status;
    }


    public void setStatus(String agendado) {
    }

    public Usuario getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(final Usuario barbeiro) {
        this.barbeiro = barbeiro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }




}
