package com.sistema.nutricao_qualidade.controller;

import com.sistema.nutricao_qualidade.model.Cliente;
import com.sistema.nutricao_qualidade.model.Endereco;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private List<Cliente> listaClientes = new ArrayList<>();

    @GetMapping("/cadastro-cliente")
    public String cadastrarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cad_clientes";
    }

    @GetMapping("/listagem-cliente")
    public String listarClientes(Model model) {
        model.addAttribute("lista", listaClientes);
        return "lista_clientes";
    }

    @PostMapping("/gravar-cliente")
    public String cadastrarFormCliente(@ModelAttribute Cliente cliente, Model model) {
        cliente.setId(listaClientes.size() + 1);
        listaClientes.add(cliente);
        return "redirect:/listagem-cliente";

    }

}
