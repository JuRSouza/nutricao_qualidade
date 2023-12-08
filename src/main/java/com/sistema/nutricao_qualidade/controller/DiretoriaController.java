package com.sistema.nutricao_qualidade.controller;

import com.sistema.nutricao_qualidade.model.Diretoria;
import com.sistema.nutricao_qualidade.model.Endereco;
import com.sistema.nutricao_qualidade.model.Usuario;
import com.sistema.nutricao_qualidade.service.DiretoriaService;
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
@RequestMapping("/diretoria")
public class DiretoriaController {

    @Autowired
    private DiretoriaService diretoriaService;

    @Autowired
    private UsuarioService usuarioService;
    //private List<Diretoria> listaDiretoria = new ArrayList<>();

    @GetMapping("/voltar-index")
    public String voltarParaIndex() {
        return "redirect:/";
    }

    @GetMapping("/cadastro-diretoria")
    public String cadastrarDiretoria(Model model) {
        model.addAttribute("diretoria", new Diretoria());
        return "cad_diretoria";
    }

    @GetMapping("/listagem-diretoria")
    public String listarDiretoria(Model model) {
        List<Diretoria> listaDiretoria = diretoriaService.listarTodosDiretorias();
        model.addAttribute("lista", listaDiretoria);
        return "lista_diretoria";
    }

    @PostMapping("/gravar-diretoria")
    public String cadastrarFormDiretoria(@ModelAttribute Diretoria diretoria, Model model) {
        if (diretoria.getId() != null) {
            diretoriaService.atualizarDiretoria(diretoria.getId(), diretoria);

        } else {
            Usuario usuario = usuarioService.getUsuarioId(diretoria.getUsuarioId());
            diretoria.setUsuario(usuario);

            diretoriaService.criarDiretoria(diretoria);
        }
        return "redirect:/diretoria/listagem-diretoria";

    }

    @GetMapping("/deletar-diretoria")
    public String excluirDiretoria(Model model, @RequestParam String id
    ) {
        Integer idCliente = Integer.parseInt(id);
        diretoriaService.deletarDiretoria(idCliente);
        return "redirect:/diretoria/listagem-diretoria";

    }
    
    @GetMapping("/editar-diretoria")
    public String editarDiretoria(Model model, @RequestParam String id
    ) {
        Integer idDiretoria = Integer.parseInt(id);
        Diretoria diretoariaEncontrado = diretoriaService.getDiretoriaId(idDiretoria);
        model.addAttribute("diretoria", diretoariaEncontrado);
        return "editar_diretoria";

    }

}
