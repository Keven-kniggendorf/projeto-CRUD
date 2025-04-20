package nevek.grupovendas.gerenciador_vendas.service;

import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import nevek.grupovendas.gerenciador_vendas.domain.Cliente;
import nevek.grupovendas.gerenciador_vendas.domain.Produto;
import nevek.grupovendas.gerenciador_vendas.domain.Usuario;
import nevek.grupovendas.gerenciador_vendas.domain.Venda;
import nevek.grupovendas.gerenciador_vendas.model.VendaDTO;
import nevek.grupovendas.gerenciador_vendas.repos.ClienteRepository;
import nevek.grupovendas.gerenciador_vendas.repos.ProdutoRepository;
import nevek.grupovendas.gerenciador_vendas.repos.UsuarioRepository;
import nevek.grupovendas.gerenciador_vendas.repos.VendaRepository;
import nevek.grupovendas.gerenciador_vendas.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public VendaService(final VendaRepository vendaRepository,
            final ClienteRepository clienteRepository, final UsuarioRepository usuarioRepository,
            final ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<VendaDTO> findAll() {
        final List<Venda> vendas = vendaRepository.findAll(Sort.by("id"));
        return vendas.stream()
                .map(venda -> mapToDTO(venda, new VendaDTO()))
                .toList();
    }

    public VendaDTO get(final Long id) {
        return vendaRepository.findById(id)
                .map(venda -> mapToDTO(venda, new VendaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final VendaDTO vendaDTO) {
        final Venda venda = new Venda();
        mapToEntity(vendaDTO, venda);
        return vendaRepository.save(venda).getId();
    }

    public void update(final Long id, final VendaDTO vendaDTO) {
        final Venda venda = vendaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(vendaDTO, venda);
        vendaRepository.save(venda);
    }

    public void delete(final Long id) {
        vendaRepository.deleteById(id);
    }

    private VendaDTO mapToDTO(final Venda venda, final VendaDTO vendaDTO) {
        vendaDTO.setId(venda.getId());
        vendaDTO.setTotalVenda(venda.getTotalVenda());
        vendaDTO.setTotalPago(venda.getTotalPago());
        vendaDTO.setDesconto(venda.getDesconto());
        vendaDTO.setDataHoraCriacao(venda.getDataHoraCriacao());
        vendaDTO.setUltimaAtualizacao(venda.getUltimaAtualizacao());
        vendaDTO.setCliente(venda.getCliente() == null ? null : venda.getCliente().getId());
        vendaDTO.setUsuario(venda.getUsuario() == null ? null : venda.getUsuario().getId());
        vendaDTO.setProduto(venda.getProdutos().stream()
                .map(produto -> produto.getId())
                .toList());
        return vendaDTO;
    }

    private Venda mapToEntity(final VendaDTO vendaDTO, final Venda venda) {
        venda.setTotalVenda(vendaDTO.getTotalVenda());
        venda.setTotalPago(vendaDTO.getTotalPago());
        venda.setDesconto(vendaDTO.getDesconto());
        venda.setDataHoraCriacao(vendaDTO.getDataHoraCriacao());
        venda.setUltimaAtualizacao(vendaDTO.getUltimaAtualizacao());
        final Cliente cliente = vendaDTO.getCliente() == null ? null : clienteRepository.findById(vendaDTO.getCliente())
                .orElseThrow(() -> new NotFoundException("cliente not found"));
        venda.setCliente(cliente);
        final Usuario usuario = vendaDTO.getUsuario() == null ? null : usuarioRepository.findById(vendaDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("usuario not found"));
        venda.setUsuario(usuario);
        final List<Produto> produto = produtoRepository.findAllById(
                vendaDTO.getProduto() == null ? Collections.emptyList() : vendaDTO.getProduto());
        if (produto.size() != (vendaDTO.getProduto() == null ? 0 : vendaDTO.getProduto().size())) {
            throw new NotFoundException("one of produto not found");
        }
//        venda.setProduto(new List<>(produto) {
//        });
        return venda;
    }

}
