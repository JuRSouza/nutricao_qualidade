package com.sistema.nutricao_qualidade.controller;

import com.sistema.nutricao_qualidade.model.Endereco;
import com.sistema.nutricao_qualidade.model.Funcionario;
import com.sistema.nutricao_qualidade.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {
    
    private List<Funcionario> listaFunc = new ArrayList<>();

    @GetMapping("/") //Define a rota que será chamada.
    public String index() {
        return "index"; //aponta para o html que será chamado.
    }

    @GetMapping("/cadastro-funcionario")
    public String cadastrarFunc(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "cad_funcionario";
    }

    @GetMapping("/listagemFunc")
    public String listarFunc(Model model) {
        model.addAttribute( "lista", listaFunc);
        return "lista_funcionarios";
    }

    @PostMapping("/gravarFunc")
    public String cadastrarFormulario(@ModelAttribute Funcionario funcionario, Model model){
        funcionario.setId(listaFunc.size() + 1);
        listaFunc.add(funcionario);
        
       /* Endereco endereco = enderecoService.createOrGet(funcionario.getEndereco());enderecoService.createOrGet(funcionario.getEndereco());
        funcionario.setEndereco(endereco);
        
        Usuario usuario = usuarioService.createOrGet(funcionario.getUsuario());
        funcionarioService.save(funcionario);*/
        return "redirect:/listagemFunc";
        
    }
}
