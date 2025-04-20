package nevek.grupovendas.gerenciador_vendas.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AgendamentoResponseDTO {

    private UUID id;
    private LocalDateTime dataHora;
    private BigDecimal valorTotal;
    private String status;
    private String nomeCliente;
    private String nomeBarbeiro;
    private List<String> nomesProdutos;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeBarbeiro() {
        return nomeBarbeiro;
    }

    public void setNomeBarbeiro(String nomeBarbeiro) {
        this.nomeBarbeiro = nomeBarbeiro;
    }

    public List<String> getNomesProdutos() {
        return nomesProdutos;
    }

    public void setNomesProdutos(List<String> nomesProdutos) {
        this.nomesProdutos = nomesProdutos;
    }
}
