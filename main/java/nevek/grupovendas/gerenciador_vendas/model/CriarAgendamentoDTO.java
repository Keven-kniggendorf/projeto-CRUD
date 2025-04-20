package nevek.grupovendas.gerenciador_vendas.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CriarAgendamentoDTO {

    private LocalDateTime dataHora;
    private UUID cliente;
    private UUID barbeiro;
    private List<UUID> produtos;

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public UUID getCliente() {
        return cliente;
    }

    public void setCliente(UUID cliente) {
        this.cliente = cliente;
    }

    public UUID getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(UUID barbeiro) {
        this.barbeiro = barbeiro;
    }

    public List<UUID> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<UUID> produtos) {
        this.produtos = produtos;
    }
}
