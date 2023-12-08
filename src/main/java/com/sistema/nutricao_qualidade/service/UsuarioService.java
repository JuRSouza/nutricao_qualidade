package com.sistema.nutricao_qualidade.service;

import com.sistema.nutricao_qualidade.model.Usuario;
import com.sistema.nutricao_qualidade.model.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void cadastrarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    /*  public Usuario criarUsuario(Usuario usuario) {
        usuario.setId(null);
        usuarioRepository.save(usuario);
        return usuario;
    }
     */
    public Usuario atualizarUsuario(Integer usuarioId, Usuario usuarioRequest) {
        Usuario usuario = getUsuarioId(usuarioId);

        usuario.setLogin(usuarioRequest.getLogin());
        usuario.setSenha(usuarioRequest.getSenha());
        usuario.setConfirmar_Senha(usuarioRequest.getConfirmar_Senha());
        usuario.setTipoAcesso(usuarioRequest.getTipoAcesso());
        usuario.setUltimo_Login(usuarioRequest.getUltimo_Login());
        usuario.setLogin(usuarioRequest.getLogin());

        return usuario;
    }

    public Usuario getUsuarioId(Integer usuarioId) {//tras todos os dados pelo id
        return usuarioRepository.findById(usuarioId).orElse(null);
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public void deletarUsuario(Integer usuarioId) {
        Usuario usuario = getUsuarioId(usuarioId);
        usuarioRepository.deleteById(usuario.getId());
    }

}
