package nevek.grupovendas.gerenciador_vendas.repos;

import java.util.List;
import nevek.grupovendas.gerenciador_vendas.domain.Cliente;
import nevek.grupovendas.gerenciador_vendas.domain.Produto;
import nevek.grupovendas.gerenciador_vendas.domain.Usuario;
import nevek.grupovendas.gerenciador_vendas.domain.Venda;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VendaRepository extends JpaRepository<Venda, Long> {

//    Venda findFirstByCliente(Cliente cliente);
//
//    Venda findFirstByUsuario(Usuario usuario);
//
//    Venda findFirstByProdutos(List<Produto> produto);
//
//    List<Venda> findAllByProdutos(List<Produto> produto);
//
//    Iterable<Object> findAllByProduto(List<Produto> produto);

    List<Venda> findAllByProdutosContaining(List<Produto> produtos);

    Venda findFirstByUsuario(Usuario usuario);

    Venda findFirstByCliente(Cliente cliente);
}
