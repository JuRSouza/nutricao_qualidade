package com.sistema.nutricao_qualidade.controller;

import com.sistema.nutricao_qualidade.model.Diretoria;
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
@RequestMapping("/diretoria")
public class DiretoriaController {

    private List<Diretoria> listaDiretoria = new ArrayList<>();

    @GetMapping("/cadastro-diretoria")
    public String cadastrarDiretoria(Model model) {
        model.addAttribute("diretoria", new Diretoria());
        return "cad_diretoria";
    }

    @GetMapping("/listagem-diretoria")
    public String listarDiretoria(Model model) {
        model.addAttribute("lista", listaDiretoria);
        return "lista_diretoria";
    }

    @PostMapping("/gravar-diretoria")
    public String cadastrarFormDiretoria(@ModelAttribute Diretoria diretoria, Model model) {
        diretoria.setId(listaDiretoria.size() + 1);
        listaDiretoria.add(diretoria);
        return "redirect:/listagem-diretoria";

    }

}
