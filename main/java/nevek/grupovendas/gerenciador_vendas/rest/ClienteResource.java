package nevek.grupovendas.gerenciador_vendas.rest;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import nevek.grupovendas.gerenciador_vendas.model.ClienteDTO;
import nevek.grupovendas.gerenciador_vendas.service.ClienteService;
import nevek.grupovendas.gerenciador_vendas.util.ReferencedException;
import nevek.grupovendas.gerenciador_vendas.util.ReferencedWarning;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteResource {

    private final ClienteService clienteService;

    public ClienteResource(final ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(clienteService.get(id));
    }

    @PostMapping
    public ResponseEntity<UUID> createCliente(@RequestBody @Valid final ClienteDTO clienteDTO) {
        final UUID createdId = clienteService.create(clienteDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateCliente(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final ClienteDTO clienteDTO) {
        clienteService.update(id, clienteDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable(name = "id") final UUID id) {
        final ReferencedWarning referencedWarning = clienteService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
