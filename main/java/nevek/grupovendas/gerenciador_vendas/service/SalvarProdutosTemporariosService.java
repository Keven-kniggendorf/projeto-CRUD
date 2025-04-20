package nevek.grupovendas.gerenciador_vendas.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SalvarProdutosTemporariosService {

    private List<UUID> produtosSelecionados = new ArrayList<>();

    public void salvarTemporariamente(List<UUID> uuids) {
        produtosSelecionados.clear();
        produtosSelecionados.addAll(uuids);
    }

    public List<UUID> getProdutosSelecionados() {
        return produtosSelecionados;
    }


    public void limpar() {
        produtosSelecionados.clear();
    }




}
