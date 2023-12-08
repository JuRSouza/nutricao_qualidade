package com.sistema.nutricao_qualidade.controller;

import com.sistema.nutricao_qualidade.model.Endereco;
import com.sistema.nutricao_qualidade.model.Funcionario;
import com.sistema.nutricao_qualidade.model.Usuario;
import com.sistema.nutricao_qualidade.service.EnderecoService;
import com.sistema.nutricao_qualidade.service.FuncionarioService;
import com.sistema.nutricao_qualidade.service.UsuarioService;
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
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private FuncionarioService funcionarioService;

    //private List<Funcionario> listaFunc = new ArrayList<>();
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
        List<Funcionario> listaFunc = funcionarioService.listarTodosFuncionarios();
        model.addAttribute("lista", listaFunc);
        return "lista_funcionarios";
    }

    /*
    @PostMapping("/gravarFunc")
    public String cadastrarFormulario(@ModelAttribute Funcionario funcionario, @RequestParam(name = "usuarioId") Integer usuarioId, @RequestParam(name = "enderecoId") Integer enderecoId, Model model) {
      
        Usuario usuario = usuarioService.getUsuarioId(usuarioId);
        Endereco endereco = enderecoService.getEnderecoId(enderecoId);

        funcionario.setUsuario(usuario);
        funcionario.setEndereco(endereco);

        funcionarioService.criarFuncionario(funcionario);

        return "redirect:/funcionario/listagemFunc";

    }*/
    @PostMapping("/gravarFunc")
    public String cadastrarFormulario(@ModelAttribute Funcionario funcionario, Model model) {

        if (funcionario.getId() != null) {
            funcionarioService.atualizarFuncionario(funcionario.getId(), funcionario);
        } else {
            Usuario usuario = usuarioService.getUsuarioId(funcionario.getUsuarioId());
            Endereco endereco = enderecoService.getEnderecoId(funcionario.getEnderecoId());

            funcionario.setUsuario(usuario);
            funcionario.setEndereco(endereco);

            funcionarioService.criarFuncionario(funcionario);
        }

        return "redirect:/funcionario/listagemFunc";
    }

    @GetMapping("/deletar-funcionario")
    public String excluirFuncionario(Model model, @RequestParam String id
    ) {
        Integer idFuncionario = Integer.parseInt(id);
        funcionarioService.deletarFuncionario(idFuncionario);
        return "redirect:/funcionario/listagemFunc";

    }
    
    @GetMapping("/editar-funcionario")
    public String editarFuncionario(Model model, @RequestParam String id
    ) {
        Integer idFuncionario = Integer.parseInt(id);
        Funcionario funcionarioEncontrado = funcionarioService.getFuncionarioId(idFuncionario);
        model.addAttribute("funcionario", funcionarioEncontrado);
        return "editar_funcionario";

    }

}
