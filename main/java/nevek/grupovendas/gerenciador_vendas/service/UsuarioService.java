package nevek.grupovendas.gerenciador_vendas.service;

import java.util.List;
import java.util.UUID;
import nevek.grupovendas.gerenciador_vendas.domain.Produto;
import nevek.grupovendas.gerenciador_vendas.domain.Usuario;
import nevek.grupovendas.gerenciador_vendas.domain.Venda;
import nevek.grupovendas.gerenciador_vendas.model.UsuarioDTO;
import nevek.grupovendas.gerenciador_vendas.repos.ProdutoRepository;
import nevek.grupovendas.gerenciador_vendas.repos.UsuarioRepository;
import nevek.grupovendas.gerenciador_vendas.repos.VendaRepository;
import nevek.grupovendas.gerenciador_vendas.util.NotFoundException;
import nevek.grupovendas.gerenciador_vendas.util.ReferencedWarning;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    public UsuarioService(final UsuarioRepository usuarioRepository,
            final VendaRepository vendaRepository, final ProdutoRepository produtoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<UsuarioDTO> findAll() {
        final List<Usuario> usuarios = usuarioRepository.findAll(Sort.by("id"));
        return usuarios.stream()
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .toList();
    }

    public UsuarioDTO get(final UUID id) {
        return usuarioRepository.findById(id)
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final UsuarioDTO usuarioDTO) {
        final Usuario usuario = new Usuario();
        mapToEntity(usuarioDTO, usuario);
        return usuarioRepository.save(usuario).getId();
    }

    public void update(final UUID id, final UsuarioDTO usuarioDTO) {
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usuarioDTO, usuario);
        usuarioRepository.save(usuario);
    }

    public void delete(final UUID id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO mapToDTO(final Usuario usuario, final UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setUsuario(usuario.getUsuario());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setPerfil(usuario.getPerfil());
        usuarioDTO.setEstado(usuario.getEstado());
        usuarioDTO.setDataHoraCriacao(usuario.getDataHoraCriacao());
        usuarioDTO.setUltimoLogin(usuario.getUltimoLogin());
        return usuarioDTO;
    }

    private Usuario mapToEntity(final UsuarioDTO usuarioDTO, final Usuario usuario) {
        usuario.setNome(usuarioDTO.getNome());
        usuario.setUsuario(usuarioDTO.getUsuario());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setPerfil(usuarioDTO.getPerfil());
        usuario.setEstado(usuarioDTO.getEstado());
        usuario.setDataHoraCriacao(usuarioDTO.getDataHoraCriacao());
        usuario.setUltimoLogin(usuarioDTO.getUltimoLogin());
        return usuario;
    }

    public boolean usuarioExists(final String usuario) {
        return usuarioRepository.existsByUsuarioIgnoreCase(usuario);
    }

    public ReferencedWarning getReferencedWarning(final UUID id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Venda usuarioVenda = vendaRepository.findFirstByUsuario(usuario);
        if (usuarioVenda != null) {
            referencedWarning.setKey("usuario.venda.usuario.referenced");
            referencedWarning.addParam(usuarioVenda.getId());
            return referencedWarning;
        }
        final Produto usuarioProduto = produtoRepository.findFirstByUsuario(usuario);
        if (usuarioProduto != null) {
            referencedWarning.setKey("usuario.produto.usuario.referenced");
            referencedWarning.addParam(usuarioProduto.getId());
            return referencedWarning;
        }
        return null;
    }

}
