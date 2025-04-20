package nevek.grupovendas.gerenciador_vendas.model;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoSelecionadoDTO {



    public static class ProdutoSelecionado {
        private UUID id;
        private String nome;
        private BigDecimal preco;

        public ProdutoSelecionado(UUID id, String nome, BigDecimal preco) {
            this.id = id;
            this.nome = nome;
            this.preco = preco;
        }


        public UUID getId() {
            return id;
        }
        public void setId(UUID id) {
            this.id = id;
        }
        public String getNome() {
            return nome;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }
        public BigDecimal getPreco() {
            return preco;
        }
        public void setPreco(BigDecimal preco) {
            this.preco = preco;
        }

    }


}
