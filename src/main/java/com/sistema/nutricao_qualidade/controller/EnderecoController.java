
package com.sistema.nutricao_qualidade.controller;

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
@RequestMapping("/endereco")
public class EnderecoController {
    
    private List<Endereco> listaEnd = new ArrayList<>();
    
     @GetMapping("/cadastro-endereco")
    public String cadastrarEnd(Model model) {
        model.addAttribute("endereco", new Endereco());
        return "cad_endereco";
    }
    
    @GetMapping("/listagem-endereco")
    public String listarEnd(Model model) {
        model.addAttribute( "lista", listaEnd);
        return "lista_enderecos";
    }
    
    @PostMapping("/gravar-endereco")
    public String cadastrarFormEndereco(@ModelAttribute Endereco endereco, Model model){
        endereco.setId(listaEnd.size() + 1);
        listaEnd.add(endereco);
        return "redirect:/listagem-endereco";
        
    }
}
