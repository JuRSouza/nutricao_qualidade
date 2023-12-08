package com.sistema.nutricao_qualidade.service;

import com.sistema.nutricao_qualidade.model.Cliente;
import com.sistema.nutricao_qualidade.model.Funcionario;
import com.sistema.nutricao_qualidade.model.Relatorio;
import com.sistema.nutricao_qualidade.model.RelatorioRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    @Autowired
    RelatorioRepository relatorioRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    FuncionarioService funcionarioService;

    public Relatorio criarRelatorio(Relatorio relatorio) {
        relatorio.setId(null);

        Cliente cliente = clienteService.getClienteId(relatorio.getClienteId());
        Funcionario nutricionista = funcionarioService.getFuncionarioId(relatorio.getFuncionarioId());

        if (cliente != null && nutricionista != null) {
            relatorio.setCliente(cliente);
            relatorio.setFuncionario(nutricionista);

            return relatorioRepository.save(relatorio);
        } else {
            throw new RuntimeException("IDs de cliente ou nutricionista inválidos");
        }
    }

    public Relatorio atualizarRelatorio(Integer relatorioId, Relatorio relatorioRequest) {
        Relatorio relatorio = getRelatorioId(relatorioId);

        if (relatorio != null) {
            Cliente cliente = clienteService.getClienteId(relatorioRequest.getClienteId());
            Funcionario funcionario = funcionarioService.getFuncionarioId(relatorioRequest.getFuncionarioId());

            relatorio.setData(relatorioRequest.getData());
            relatorio.setHora_Entrada(relatorioRequest.getHora_Entrada());
            relatorio.setHora_Saida(relatorioRequest.getHora_Saida());
            relatorio.setTexto_Relatorio(relatorioRequest.getTexto_Relatorio());
            relatorio.setCliente(cliente);
            relatorio.setFuncionario(funcionario);

            return relatorioRepository.save(relatorio);
        } else {
            throw new RuntimeException("Relatório não encontrado com o ID: " + relatorioId);

        }
    }

    public Relatorio getRelatorioId(Integer relatorioId) {
        return relatorioRepository.findById(relatorioId).orElse(null);
    }

    public List<Relatorio> listarTodosRelatorios() {
        return relatorioRepository.findAll();
    }

    public void deletarRelatorio(Integer relatorioId) {
        Relatorio relatorio = getRelatorioId(relatorioId);
        relatorioRepository.deleteById(relatorio.getId());
    }

}
