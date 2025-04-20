package nevek.grupovendas.gerenciador_vendas.rest;

import nevek.grupovendas.gerenciador_vendas.domain.Agendamento;
import nevek.grupovendas.gerenciador_vendas.model.StatusAgendamento;
import nevek.grupovendas.gerenciador_vendas.repos.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @PostMapping
    public ResponseEntity<?> criarAgendamento(@RequestBody Agendamento agendamento) {

        List<Agendamento> existentes = agendamentoRepository.findByDataHoraAndBarbeiro(agendamento.getDataHora(), agendamento.getBarbeiro());
        if (!existentes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Horário já agendado para este barbeiro. Por favor, escolha outro horário.");
        }
        agendamento.setStatus(StatusAgendamento.AGENDADO);
        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
        return ResponseEntity.ok(agendamentoSalvo);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getAgendamento(@PathVariable UUID id) {
        return agendamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
