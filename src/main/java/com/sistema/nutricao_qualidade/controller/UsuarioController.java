package com.sistema.nutricao_qualidade.controller;

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
@RequestMapping("/usuario")
public class UsuarioController {

    private List<Usuario> listaUsuario = new ArrayList<>();

   /* @GetMapping("/") //Define a rota que será chamada.
    public String index() {
        return "index"; //aponta para o html que será chamado.
    }*/

    @GetMapping("/cadastro-usuario")
    public String cadastrarUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cad_usuario";
    }

    @GetMapping("/listagem-usuario")
    public String listarUsuario(Model model) {
        model.addAttribute("lista", listaUsuario);
        return "lista_usuarios";
    }

    @PostMapping("/gravar-usuario")
    public String cadastrarFormUsuario(@ModelAttribute Usuario usuario, Model model) {
        usuario.setId(listaUsuario.size() + 1);
        listaUsuario.add(usuario);
        return "redirect:/listagem-usuario";

    }
}
