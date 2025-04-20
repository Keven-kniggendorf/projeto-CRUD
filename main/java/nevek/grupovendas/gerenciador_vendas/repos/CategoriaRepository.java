package nevek.grupovendas.gerenciador_vendas.repos;

import java.util.UUID;
import nevek.grupovendas.gerenciador_vendas.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
}
