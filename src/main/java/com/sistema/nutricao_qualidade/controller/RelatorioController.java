package com.sistema.nutricao_qualidade.controller;

import com.sistema.nutricao_qualidade.model.Cliente;
import com.sistema.nutricao_qualidade.model.Funcionario;
import com.sistema.nutricao_qualidade.model.Relatorio;
import com.sistema.nutricao_qualidade.service.ClienteService;
import com.sistema.nutricao_qualidade.service.FuncionarioService;
import com.sistema.nutricao_qualidade.service.RelatorioService;
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
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private RelatorioService relatorioService;

    //private List<Relatorio> listaRelatorio = new ArrayList<>();
    @GetMapping("/voltar-index")
    public String voltarParaIndex() {
        return "redirect:/";
    }

    @GetMapping("/cadastro-relatorio")
    public String cadastrarRelatorio(Model model) {
        model.addAttribute("relatorio", new Relatorio());
        return "cad_relatorios";
    }

    @GetMapping("/listagem-relatorios")
    public String listarRelatorio(Model model) {
        List<Relatorio> listaRelatorio = relatorioService.listarTodosRelatorios();
        model.addAttribute("lista", listaRelatorio);
        return "lista_relatorios";
    }

    @PostMapping("/gravar-relatorio")
    public String cadastrarFormRelatorio(@ModelAttribute Relatorio relatorio, Model model) {

        if (relatorio.getId() != null) {
            relatorioService.atualizarRelatorio(relatorio.getId(), relatorio);
        } else {
            Cliente cliente = clienteService.getClienteId(relatorio.getClienteId());
            Funcionario nutricionista = funcionarioService.getFuncionarioId(relatorio.getFuncionarioId());

            relatorio.setCliente(cliente);
            relatorio.setFuncionario(nutricionista);

            relatorio.setClienteId(cliente.getId());
            relatorio.setFuncionarioId(nutricionista.getId());

            relatorioService.criarRelatorio(relatorio);
        }
        return "redirect:/listagem-relatorios";
    }

    @PostMapping("/buscar-cliente-nutricionista")
    public String buscarClienteNutricionista(@ModelAttribute Relatorio relatorio, Model model) {
        // try {
        String clienteNome = clienteService.getNomeById(relatorio.getClienteId());
        String nutricionistaNome = funcionarioService.getNomeById(relatorio.getFuncionarioId());

        model.addAttribute("clienteNome", clienteNome);
        model.addAttribute("nutricionistaNome", nutricionistaNome);

        return "cad_relatorios";
    } //catch (Exception e) {
    // model.addAttribute("erro", "Erro ao buscar cliente/nutricionista: " + e.getMessage());
    // return "cad_relatorios";
    // }
    // }

    @GetMapping("/deletar-relatorio")
    public String excluirRelatorio(Model model, @RequestParam String id
    ) {
        Integer idRelatorio = Integer.parseInt(id);
        relatorioService.deletarRelatorio(idRelatorio);
        return "redirect:/relatorio/listagem-relatorio";

    }

    @GetMapping("/editar-relatorio")
    public String editarRelatorio(Model model, @RequestParam String id
    ) {
        Integer idRelatorio = Integer.parseInt(id);
        Relatorio relatorioEncontrado = relatorioService.getRelatorioId(idRelatorio);
        model.addAttribute("relatorio", relatorioEncontrado);
        return "editar_relatorio";

    }
}
