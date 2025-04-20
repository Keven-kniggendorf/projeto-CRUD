package nevek.grupovendas.gerenciador_vendas.service;

import java.util.List;
import java.util.UUID;
import nevek.grupovendas.gerenciador_vendas.domain.Categoria;
import nevek.grupovendas.gerenciador_vendas.domain.Produto;
import nevek.grupovendas.gerenciador_vendas.model.CategoriaDTO;
import nevek.grupovendas.gerenciador_vendas.repos.CategoriaRepository;
import nevek.grupovendas.gerenciador_vendas.repos.ProdutoRepository;
import nevek.grupovendas.gerenciador_vendas.util.NotFoundException;
import nevek.grupovendas.gerenciador_vendas.util.ReferencedWarning;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;

    public CategoriaService(final CategoriaRepository categoriaRepository,
            final ProdutoRepository produtoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<CategoriaDTO> findAll() {
        final List<Categoria> categorias = categoriaRepository.findAll(Sort.by("id"));
        return categorias.stream()
                .map(categoria -> mapToDTO(categoria, new CategoriaDTO()))
                .toList();
    }

    public CategoriaDTO get(final UUID id) {
        return categoriaRepository.findById(id)
                .map(categoria -> mapToDTO(categoria, new CategoriaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final CategoriaDTO categoriaDTO) {
        final Categoria categoria = new Categoria();
        mapToEntity(categoriaDTO, categoria);
        return categoriaRepository.save(categoria).getId();
    }

    public void update(final UUID id, final CategoriaDTO categoriaDTO) {
        final Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(categoriaDTO, categoria);
        categoriaRepository.save(categoria);
    }

    public void delete(final UUID id) {
        categoriaRepository.deleteById(id);
    }

    private CategoriaDTO mapToDTO(final Categoria categoria, final CategoriaDTO categoriaDTO) {
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNome(categoria.getNome());
        categoriaDTO.setDescricao(categoria.getDescricao());
        return categoriaDTO;
    }

    private Categoria mapToEntity(final CategoriaDTO categoriaDTO, final Categoria categoria) {
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());
        return categoria;
    }

    public ReferencedWarning getReferencedWarning(final UUID id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Produto categoriaProduto = produtoRepository.findFirstByCategoria(categoria);
        if (categoriaProduto != null) {
            referencedWarning.setKey("categoria.produto.categoria.referenced");
            referencedWarning.addParam(categoriaProduto.getId());
            return referencedWarning;
        }
        return null;
    }

}
