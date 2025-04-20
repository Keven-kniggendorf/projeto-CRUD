package nevek.grupovendas.gerenciador_vendas.repos;

import java.util.UUID;
import nevek.grupovendas.gerenciador_vendas.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
