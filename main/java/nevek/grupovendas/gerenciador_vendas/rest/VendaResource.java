package nevek.grupovendas.gerenciador_vendas.rest;

import jakarta.validation.Valid;
import java.util.List;
import nevek.grupovendas.gerenciador_vendas.model.VendaDTO;
import nevek.grupovendas.gerenciador_vendas.service.VendaService;
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
@RequestMapping(value = "/api/vendas", produces = MediaType.APPLICATION_JSON_VALUE)
public class VendaResource {

    private final VendaService vendaService;

    public VendaResource(final VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping
    public ResponseEntity<List<VendaDTO>> getAllVendas() {
        return ResponseEntity.ok(vendaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> getVenda(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(vendaService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createVenda(@RequestBody @Valid final VendaDTO vendaDTO) {
        final Long createdId = vendaService.create(vendaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateVenda(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final VendaDTO vendaDTO) {
        vendaService.update(id, vendaDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenda(@PathVariable(name = "id") final Long id) {
        vendaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
