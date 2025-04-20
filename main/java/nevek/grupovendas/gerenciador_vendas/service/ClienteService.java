package nevek.grupovendas.gerenciador_vendas.service;

import java.util.List;
import java.util.UUID;
import nevek.grupovendas.gerenciador_vendas.domain.Cliente;
import nevek.grupovendas.gerenciador_vendas.domain.Venda;
import nevek.grupovendas.gerenciador_vendas.model.ClienteDTO;
import nevek.grupovendas.gerenciador_vendas.repos.ClienteRepository;
import nevek.grupovendas.gerenciador_vendas.repos.VendaRepository;
import nevek.grupovendas.gerenciador_vendas.util.NotFoundException;
import nevek.grupovendas.gerenciador_vendas.util.ReferencedWarning;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final VendaRepository vendaRepository;

    public ClienteService(final ClienteRepository clienteRepository,
            final VendaRepository vendaRepository) {
        this.clienteRepository = clienteRepository;
        this.vendaRepository = vendaRepository;
    }

    public List<ClienteDTO> findAll() {
        final List<Cliente> clientes = clienteRepository.findAll(Sort.by("id"));
        return clientes.stream()
                .map(cliente -> mapToDTO(cliente, new ClienteDTO()))
                .toList();
    }

    public ClienteDTO get(final UUID id) {
        return clienteRepository.findById(id)
                .map(cliente -> mapToDTO(cliente, new ClienteDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UUID create(final ClienteDTO clienteDTO) {
        final Cliente cliente = new Cliente();
        mapToEntity(clienteDTO, cliente);
        return clienteRepository.save(cliente).getId();
    }

    public void update(final UUID id, final ClienteDTO clienteDTO) {
        final Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(clienteDTO, cliente);
        clienteRepository.save(cliente);
    }

    public void delete(final UUID id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO mapToDTO(final Cliente cliente, final ClienteDTO clienteDTO) {
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEmail(cliente.getEmail());
        return clienteDTO;
    }

    private Cliente mapToEntity(final ClienteDTO clienteDTO, final Cliente cliente) {
        cliente.setNome(clienteDTO.getNome());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEmail(clienteDTO.getEmail());
        return cliente;
    }

    public ReferencedWarning getReferencedWarning(final UUID id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Venda clienteVenda = vendaRepository.findFirstByCliente(cliente);
        if (clienteVenda != null) {
            referencedWarning.setKey("cliente.venda.cliente.referenced");
            referencedWarning.addParam(clienteVenda.getId());
            return referencedWarning;
        }
        return null;
    }

}
