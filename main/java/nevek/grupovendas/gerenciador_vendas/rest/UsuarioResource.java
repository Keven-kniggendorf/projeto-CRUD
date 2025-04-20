package nevek.grupovendas.gerenciador_vendas.rest;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import nevek.grupovendas.gerenciador_vendas.model.UsuarioDTO;
import nevek.grupovendas.gerenciador_vendas.service.UsuarioService;
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
@RequestMapping(value = "/api/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioResource {

    private final UsuarioService usuarioService;

    public UsuarioResource(final UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(usuarioService.get(id));
    }

    @PostMapping
    public ResponseEntity<UUID> createUsuario(@RequestBody @Valid final UsuarioDTO usuarioDTO) {
        final UUID createdId = usuarioService.create(usuarioDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateUsuario(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final UsuarioDTO usuarioDTO) {
        usuarioService.update(id, usuarioDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable(name = "id") final UUID id) {
        final ReferencedWarning referencedWarning = usuarioService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
