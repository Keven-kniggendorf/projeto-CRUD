package nevek.grupovendas.gerenciador_vendas.repos;

import java.util.List;
import java.util.UUID;
import nevek.grupovendas.gerenciador_vendas.domain.Categoria;
import nevek.grupovendas.gerenciador_vendas.domain.Produto;
import nevek.grupovendas.gerenciador_vendas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    Produto findFirstByUsuario(Usuario usuario);

    Produto findFirstByCategoria(Categoria categoria);

    @Query("SELECT p FROM Produto p WHERE p.categorias = :categorias")
    List<Produto> findByCategoria(@Param("categorias") String categorias);

}
