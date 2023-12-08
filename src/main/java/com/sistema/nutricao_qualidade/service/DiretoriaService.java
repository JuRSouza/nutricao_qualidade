package com.sistema.nutricao_qualidade.service;

import com.sistema.nutricao_qualidade.model.Diretoria;
import com.sistema.nutricao_qualidade.model.DiretoriaRepository;
import com.sistema.nutricao_qualidade.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiretoriaService {

    @Autowired
    DiretoriaRepository diretoriaRepository;

    @Autowired
    UsuarioService usuarioService;

    public Diretoria criarDiretoria(Diretoria diretoria) {
        diretoria.setId(null);
        diretoriaRepository.save(diretoria);
        return diretoria;
    }

    public Diretoria atualizarDiretoria(Integer diretoriaId, Diretoria diretoriaRequest) {
        Diretoria diretoria = getDiretoriaId(diretoriaId);

        Usuario usuario = usuarioService.getUsuarioId(diretoriaRequest.getUsuarioId());

        diretoria.setNome(diretoriaRequest.getNome());
        diretoria.setSobrenome(diretoriaRequest.getSobrenome());
        diretoria.setEmail(diretoriaRequest.getEmail());
        diretoria.setTelefone(diretoriaRequest.getTelefone());
        diretoria.setCelular(diretoriaRequest.getCelular());
        diretoria.setUsuario(usuario);

        return diretoriaRepository.save(diretoria);
    }

    public Diretoria getDiretoriaId(Integer diretoriaId) {
        return diretoriaRepository.findById(diretoriaId).orElse(null);
    }

    public List<Diretoria> listarTodosDiretorias() {
        return diretoriaRepository.findAll();
    }

    public void deletarDiretoria(Integer diretoriaId) {
        Diretoria diretoria = getDiretoriaId(diretoriaId);
        diretoriaRepository.deleteById(diretoria.getId());
    }
}
