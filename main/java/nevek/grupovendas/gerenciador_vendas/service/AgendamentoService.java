package nevek.grupovendas.gerenciador_vendas.service;

import nevek.grupovendas.gerenciador_vendas.domain.*;
import nevek.grupovendas.gerenciador_vendas.model.AgendamentoResponseDTO;
import nevek.grupovendas.gerenciador_vendas.model.CriarAgendamentoDTO;
import nevek.grupovendas.gerenciador_vendas.model.StatusAgendamento;
import nevek.grupovendas.gerenciador_vendas.repos.*;
import nevek.grupovendas.gerenciador_vendas.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private SalvarProdutosTemporariosService salvarProdutosTemporariosService;
    @Autowired
    private VendaRepository vendaRepository;

    public AgendamentoResponseDTO agendar(CriarAgendamentoDTO dto) {
//         Busca entidades
        Cliente cliente = clienteRepository.findById(dto.getCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Usuario barbeiro = usuarioRepository.findById(dto.getBarbeiro())
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));

        List<Produto> produtos = produtoRepository.findAllById(dto.getProdutos());

        // Calcula valor total
        BigDecimal total = produtos.stream()
                .map(p -> p.getPreco() != null ? p.getPreco() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Cria entidade Agendamento
        Agendamento agendamento = new Agendamento();
        agendamento.setDataHora(dto.getDataHora());Cliente clientes = clienteRepository.findById(dto.getCliente())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
        Usuario barbeiros = usuarioRepository.findById(dto.getBarbeiro())
                .orElseThrow(() -> new NotFoundException("Barbeiro não encontrado"));
        agendamento.setBarbeiro(barbeiros);
        agendamento.setCliente(clientes);
        agendamento.setBarbeiro(barbeiros);
        agendamento.setProduto((Produto) produtos);
        agendamento.setValorTotal(total);
        agendamento.setStatus(StatusAgendamento.AGENDADO);

        // Salva no banco
        agendamento = agendamentoRepository.save(agendamento);

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setUsuario(barbeiros);
        venda.setDataHoraCriacao(LocalDateTime.now());
        venda.setUltimaAtualizacao(LocalDateTime.now());
        venda.setTotalVenda(total.doubleValue());
        venda.setTotalPago(total.doubleValue());
        venda.setDesconto(0.0);
        venda.setProduto(produtos);
        vendaRepository.save(venda);

        // Monta resposta
        AgendamentoResponseDTO resposta = new AgendamentoResponseDTO();
        resposta.setId(agendamento.getId());
        resposta.setDataHora(agendamento.getDataHora());
        resposta.setValorTotal(agendamento.getValorTotal());
        resposta.setStatus(agendamento.getStatus().name());
        resposta.setNomeCliente(cliente.getNome());
        resposta.setNomeBarbeiro(barbeiros.getNome());
        resposta.setNomesProdutos(
                produtos.stream().map(Produto::getNome).toList()
        );

        return resposta;


    }





}
