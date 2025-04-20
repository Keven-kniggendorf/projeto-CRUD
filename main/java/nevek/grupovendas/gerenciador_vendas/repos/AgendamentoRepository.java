package nevek.grupovendas.gerenciador_vendas.repos;

import nevek.grupovendas.gerenciador_vendas.domain.Agendamento;
import nevek.grupovendas.gerenciador_vendas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {

        List<Agendamento> findByDataHoraAndBarbeiro(LocalDateTime dataHora, Usuario barbeiro);


}
