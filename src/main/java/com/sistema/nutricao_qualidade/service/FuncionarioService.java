package com.sistema.nutricao_qualidade.service;

import com.sistema.nutricao_qualidade.model.Endereco;
import com.sistema.nutricao_qualidade.model.Funcionario;
import com.sistema.nutricao_qualidade.model.FuncionarioRepository;
import com.sistema.nutricao_qualidade.model.Usuario;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EnderecoService enderecoService;

    public Funcionario criarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizarFuncionario(Integer funcionarioId, Funcionario funcionarioRequest) {
        Funcionario funcionario = getFuncionarioId(funcionarioId);

        Usuario usuario = usuarioService.getUsuarioId(funcionarioRequest.getUsuarioId());
        Endereco endereco = enderecoService.getEnderecoId(funcionarioRequest.getEnderecoId());

        funcionario.setNome(funcionarioRequest.getNome());
        funcionario.setSobrenome(funcionarioRequest.getSobrenome());
        funcionario.setData_Nascimento(funcionarioRequest.getData_Nascimento());
        funcionario.setEmail(funcionarioRequest.getEmail());
        funcionario.setTelefone(funcionarioRequest.getTelefone());
        funcionario.setCelular(funcionarioRequest.getCelular());
        funcionario.setRg(funcionarioRequest.getRg());
        funcionario.setCpf(funcionarioRequest.getCpf());
        funcionario.setCrn(funcionarioRequest.getCrn());
        funcionario.setUsuario(usuario);
        funcionario.setEndereco(endereco);

        return funcionarioRepository.save(funcionario);
    }

    public Funcionario getFuncionarioId(Integer funcionarioId) {
        return funcionarioRepository.findById(funcionarioId).orElse(null);
    }

    public String getNomeById(Integer funcionarioId) {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElse(null);
        return (funcionario != null) ? funcionario.getNome() : null;
    }

    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public void deletarFuncionario(Integer funcionarioId) {
        Funcionario funcionario = getFuncionarioId(funcionarioId);
        funcionarioRepository.deleteById(funcionario.getId());
    }
}
