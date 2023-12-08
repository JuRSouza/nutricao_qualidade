package com.sistema.nutricao_qualidade.controller;

import com.sistema.nutricao_qualidade.model.Cliente;
import com.sistema.nutricao_qualidade.model.Endereco;
import com.sistema.nutricao_qualidade.service.ClienteService;
import com.sistema.nutricao_qualidade.service.EnderecoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoService enderecoService;

    // private List<Cliente> listaClientes = new ArrayList<>();
    @GetMapping("/voltar-index")
    public String voltarParaIndex() {
        return "redirect:/";
    }

    @GetMapping("/cadastro-cliente")
    public String cadastrarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cad_clientes";
    }

    @GetMapping("/listagem-cliente")
    public String listarClientes(Model model) {
        List<Cliente> listaClientes = clienteService.listarTodosClientes();
        model.addAttribute("lista", listaClientes);
        return "lista_clientes";
    }

    @PostMapping("/gravar-cliente")
    public String cadastrarFormCliente(@ModelAttribute Cliente cliente, Model model) {

        if (cliente.getId() != null) {
            clienteService.atualizarCliente(cliente.getId(), cliente);

        } else {
            Endereco endereco = enderecoService.getEnderecoId(cliente.getEnderecoId());
            cliente.setEndereco(endereco);

            clienteService.criarCliente(cliente);

        }
        return "redirect:/cliente/listagem-cliente";
    }

    /* @GetMapping("/exibir")
    public String mostraDetalhesCliente(Model model, @RequestParam String id) {
        try {
            Integer idCliente = Integer.parseInt(id);
            Cliente registroEncontrado = clienteService.getClienteId(idCliente);

            if (registroEncontrado != null) {
                model.addAttribute("cliente", registroEncontrado);
            } else {
                model.addAttribute("mensagem", "Cliente não encontrado");
            }

        } catch (NumberFormatException e) {
            model.addAttribute("mensagem", "ID de cliente inválido");
        }

        return "exibir";
    }*/
    @GetMapping("/deletar-cliente")
    public String excluirCliente(Model model, @RequestParam String id
    ) {
        Integer idCliente = Integer.parseInt(id);
        clienteService.deletarCliente(idCliente);
        return "redirect:/cliente/listagem-cliente";

    }

    @GetMapping("/editar-cliente")
    public String editarCliente(Model model, @RequestParam String id
    ) {
        Integer idCliente = Integer.parseInt(id);
        Cliente clienteEncontrado = clienteService.getClienteId(idCliente);
        model.addAttribute("cliente", clienteEncontrado);
        return "editar_cliente";

    }
}
