package nevek.grupovendas.gerenciador_vendas.rest;

import nevek.grupovendas.gerenciador_vendas.domain.Produto;
import nevek.grupovendas.gerenciador_vendas.model.ProdutoSelecionadoDTO;
import nevek.grupovendas.gerenciador_vendas.model.SalvarProdutosTemporarioDTO;
import nevek.grupovendas.gerenciador_vendas.repos.ProdutoRepository;
import nevek.grupovendas.gerenciador_vendas.service.SalvarProdutosTemporariosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos-temporarios")
public class SalvarProdutosTemporarioController {


    private final SalvarProdutosTemporariosService salvarprodutostemporariosservice;
    private final ProdutoRepository produtoRepository;

    public SalvarProdutosTemporarioController(SalvarProdutosTemporariosService salvarprodutostemporariosservice, ProdutoRepository produtoRepository) {
        this.salvarprodutostemporariosservice = salvarprodutostemporariosservice;
        this.produtoRepository = produtoRepository;
    }

    //@GetMapping
   // public List<Produto> listarTodos() {
    //    return produtoRepository.findAll();
    //}

    @PostMapping("/selecionados")
    public ResponseEntity<Void> salvarSelecionados(@RequestBody SalvarProdutosTemporarioDTO dto) {
        salvarprodutostemporariosservice.salvarTemporariamente(dto.getUuids());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/selecionados")
    public List<ProdutoSelecionadoDTO.ProdutoSelecionado> listarSelecionados() {
        return produtoRepository.findAllById(salvarprodutostemporariosservice.getProdutosSelecionados())
                .stream()
                .map(p -> new ProdutoSelecionadoDTO.ProdutoSelecionado(p.getId(), p.getNome(), p.getPreco() ) )
                .toList();
    }


}
