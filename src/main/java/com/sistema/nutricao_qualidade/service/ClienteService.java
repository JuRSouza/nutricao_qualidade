package com.sistema.nutricao_qualidade.service;

import com.sistema.nutricao_qualidade.exception.ResourceNotFoundException;
import com.sistema.nutricao_qualidade.model.Cliente;
import com.sistema.nutricao_qualidade.model.ClienteRepository;
import com.sistema.nutricao_qualidade.model.Endereco;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    public void criarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Integer clienteId, Cliente clienteRequest) {
        Cliente cliente = getClienteId(clienteId);

        Endereco endereco = enderecoService.getEnderecoId(clienteRequest.getEnderecoId());

        cliente.setNome_Fantasia(clienteRequest.getNome_Fantasia());
        cliente.setRazao_Social(clienteRequest.getRazao_Social());
        cliente.setCnpj(clienteRequest.getCnpj());
        cliente.setCategoria(clienteRequest.getCategoria());
        cliente.setNome_responsavel_legal(clienteRequest.getNome_responsavel_legal());
        cliente.setSobrenome_responsavel_legal(clienteRequest.getSobrenome_responsavel_legal());
        cliente.setEmail_responsavel_legal(clienteRequest.getEmail_responsavel_legal());
        cliente.setTelefone_responsavel_legal(clienteRequest.getTelefone_responsavel_legal());
        cliente.setCelular_responsavel_legal(clienteRequest.getCelular_responsavel_legal());
        cliente.setNome_gerente(clienteRequest.getNome_gerente());
        cliente.setSobrenome_gerente(clienteRequest.getSobrenome_gerente());
        cliente.setEmail_gerente(clienteRequest.getEmail_gerente());
        cliente.setTelefone_gerente(clienteRequest.getTelefone_gerente());
        cliente.setCelular_gerente(clienteRequest.getCelular_gerente());
        cliente.setNome_subgerente(clienteRequest.getNome_subgerente());
        cliente.setSobrenome_subgerente(clienteRequest.getSobrenome_subgerente());
        cliente.setEmail_subgerente(clienteRequest.getEmail_subgerente());
        cliente.setTelefone_subgerente(clienteRequest.getTelefone_subgerente());
        cliente.setCelular_subgerente(clienteRequest.getCelular_subgerente());
        cliente.setEndereco(endereco);

        return clienteRepository.save(cliente);
    }

    public Cliente getClienteId(Integer clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado " + clienteId));
    }

    public String getNomeById(Integer clienteId) {// busca somente o nome pelo id. Para a tela de cad_relatorio
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        return (cliente != null) ? cliente.getNome_Fantasia() : null;
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    public void deletarCliente(Integer clienteId) {
        Cliente cliente = getClienteId(clienteId);
        clienteRepository.deleteById(cliente.getId());
    }
}
