package com.sistema.nutricao_qualidade.controller;

import com.sistema.nutricao_qualidade.model.Funcionario;
import com.sistema.nutricao_qualidade.model.Usuario;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // private List<Usuario> listaUsuario = new ArrayList<>();
    @GetMapping("/voltar-index")
    public String voltarParaIndex() {
        return "redirect:/";
    }

    @GetMapping("/cadastro-usuario")
    public String cadastrarUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cad_usuario";
    }

    @GetMapping("/listagem-usuario")
    public String listarUsuario(Model model) {
        List<Usuario> listaUsuario = usuarioService.listarTodosUsuarios();
        model.addAttribute("lista", listaUsuario);
        return "lista_usuarios";
    }

    @PostMapping("/gravar-usuario")
    public String cadastrarFormUsuario(@ModelAttribute Usuario usuario, Model model) {
        if (usuario.getId() != null) {
            usuarioService.atualizarUsuario(usuario.getId(), usuario);

        } else {

            usuarioService.cadastrarUsuario(usuario);

        }
        return "redirect:/usuario/listagem-usuario";

    }

    @GetMapping("/deletar-usuario")
    public String excluirUsuario(Model model, @RequestParam String id
    ) {
        Integer idUsuario = Integer.parseInt(id);
        usuarioService.deletarUsuario(idUsuario);
        return "redirect:/usuario/listagem-usuario";

    }

    @GetMapping("/editar-usuario")
    public String editarUsuario(Model model, @RequestParam String id
    ) {
        Integer idUsuario = Integer.parseInt(id);
        Usuario usuarioEncontrado = usuarioService.getUsuarioId(idUsuario);
        model.addAttribute("usuario", usuarioEncontrado);
        return "editar_usuario";

    }
}
