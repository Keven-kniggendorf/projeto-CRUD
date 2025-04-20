package nevek.grupovendas.gerenciador_vendas.rest;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import nevek.grupovendas.gerenciador_vendas.model.CategoriaDTO;
import nevek.grupovendas.gerenciador_vendas.service.CategoriaService;
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
@RequestMapping(value = "/api/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaResource {

    private final CategoriaService categoriaService;

    public CategoriaResource(final CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoria(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(categoriaService.get(id));
    }

    @PostMapping
    public ResponseEntity<UUID> createCategoria(
            @RequestBody @Valid final CategoriaDTO categoriaDTO) {
        final UUID createdId = categoriaService.create(categoriaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateCategoria(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final CategoriaDTO categoriaDTO) {
        categoriaService.update(id, categoriaDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable(name = "id") final UUID id) {
        final ReferencedWarning referencedWarning = categoriaService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
