package nevek.grupovendas.gerenciador_vendas.repos;

import java.util.UUID;
import nevek.grupovendas.gerenciador_vendas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    boolean existsByUsuarioIgnoreCase(String usuario);

}
