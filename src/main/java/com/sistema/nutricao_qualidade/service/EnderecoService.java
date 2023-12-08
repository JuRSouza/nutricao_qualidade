package com.sistema.nutricao_qualidade.service;

import com.sistema.nutricao_qualidade.model.Endereco;
import com.sistema.nutricao_qualidade.model.EnderecoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public Endereco criarEndereco(Endereco endereco) {
        endereco.setId(null);
        enderecoRepository.save(endereco);
        return endereco;
    }

    public Endereco atualizarEndereco(Integer enderecoId, Endereco enderecoRequest) {
        Endereco endereco = getEnderecoId(enderecoId);

        endereco.setRua(enderecoRequest.getRua());
        endereco.setNumero(enderecoRequest.getNumero());
        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setCep(enderecoRequest.getCep());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setComplemento(enderecoRequest.getComplemento());
        return endereco;
    }

    public Endereco getEnderecoId(Integer enderecoId) {
        return enderecoRepository.findById(enderecoId).orElse(null);
    }

   

    public List<Endereco> listarTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    public void deletarEndereco(Integer enderecoId) {
        Endereco endereco = getEnderecoId(enderecoId);
        enderecoRepository.deleteById(endereco.getId());
    }

}
