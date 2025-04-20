package nevek.grupovendas.gerenciador_vendas.rest;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

import nevek.grupovendas.gerenciador_vendas.domain.Produto;
import nevek.grupovendas.gerenciador_vendas.model.ProdutoDTO;
import nevek.grupovendas.gerenciador_vendas.repos.ProdutoRepository;
import nevek.grupovendas.gerenciador_vendas.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoResource {

    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;

    public ProdutoResource(final ProdutoService produtoService, ProdutoRepository produtoRepository) {
        this.produtoService = produtoService;
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/listar-todos")
    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    // Método GET para buscar todos os produtos ou filtrar por categoria

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getProdutos(@RequestParam(value = "categoria", required = false) String categoria) {
        List<ProdutoDTO> produtos;
        if (categoria != null) {
            produtos = produtoService.findByCategoria(categoria);
        } else {
            produtos = produtoService.findAll();
        }
        return ResponseEntity.ok(produtos);
    }

    @PostMapping("/selecionar-produtos")
    public ResponseEntity<Void> selecionarProdutos(@RequestBody List<UUID> produtosIds) {
        return ResponseEntity.ok().build();
    }


    @PostMapping
    public ResponseEntity<UUID> createProduto(@RequestBody @Valid final ProdutoDTO produtoDTO) {
        final UUID createdId = produtoService.create(produtoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateProduto(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final ProdutoDTO produtoDTO) {
        produtoService.update(id, produtoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable(name = "id") final UUID id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
