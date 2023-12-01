
package com.sistema.nutricao_qualidade.controller;

import com.sistema.nutricao_qualidade.model.Diretoria;
import com.sistema.nutricao_qualidade.model.Relatorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {
    
    private List<Relatorio> listaRelatorio = new ArrayList<>();
    
     @GetMapping("/cadastro-relatorio")
    public String cadastrarRelatorio(Model model) {
        model.addAttribute("relatorio", new Relatorio());
        return "cad_relatorios";
    }
    
    @GetMapping("/listagem-relatorios")
    public String listarRelatorio(Model model) {
        model.addAttribute("lista", listaRelatorio);
        return "lista_relatorios";
    }
    
     @PostMapping("/gravar-relatorio")
    public String cadastrarFormRelatorio(@ModelAttribute Relatorio relatorio, Model model) {
        relatorio.setId(listaRelatorio.size() + 1);
        listaRelatorio.add(relatorio);
        return "redirect:/listagem-relatorios";

    }
    
}
