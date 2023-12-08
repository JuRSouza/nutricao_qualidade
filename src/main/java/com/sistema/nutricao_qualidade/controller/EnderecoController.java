package com.sistema.nutricao_qualidade.controller;

import com.sistema.nutricao_qualidade.model.Endereco;
import com.sistema.nutricao_qualidade.service.EnderecoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;
    //private List<Endereco> listaEnd = new ArrayList<>();

    @GetMapping("/voltar-index")
    public String voltarParaIndex() {
        return "redirect:/";
    }

    @GetMapping("/cadastro-endereco")
    public String cadastrarEnd(Model model) {
        model.addAttribute("endereco", new Endereco());
        return "cad_endereco";
    }

    @GetMapping("/listagem-endereco")
    public String listarEnd(Model model) {
        List<Endereco> listaEnd = enderecoService.listarTodosEnderecos();
        model.addAttribute("lista", listaEnd);
        return "lista_enderecos";
    }

    @PostMapping("/gravar-endereco")
    public String cadastrarFormEndereco(@ModelAttribute Endereco endereco, Model model) {
        if (endereco.getId() != null) {
            enderecoService.atualizarEndereco(endereco.getId(), endereco);

        } else {
            enderecoService.criarEndereco(endereco);

        }
        return "redirect:/endereco/listagem-endereco";
    }

    @GetMapping("/deletar-endereco")
    public String excluirEndereco(Model model, @RequestParam String id
    ) {
        Integer idEndereco = Integer.parseInt(id);
        enderecoService.deletarEndereco(idEndereco);
        return "redirect:/endereco/listagem-endereco";

    }
    
    @GetMapping("/editar-endereco")
    public String editarEndereco(Model model, @RequestParam String id
    ) {
        Integer idEndereco = Integer.parseInt(id);
        Endereco enderecoEncontrado = enderecoService.getEnderecoId(idEndereco);
        model.addAttribute("endereco", enderecoEncontrado);
        return "editar_endereco";

    }
}
