package com.iaco.forumhub.domain.usuario;

import com.iaco.forumhub.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario criarNovoUsuario(UsuarioDetalhes dadosAutenticacao) {
        if (usuarioRepository.existsByEmail(dadosAutenticacao.email())) {
            throw new ValidacaoException("Usuário com o mesmo e-mail encontrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dadosAutenticacao.nome());
        usuario.setEmail(dadosAutenticacao.email());
        usuario.setSenha(passwordEncoder.encode(dadosAutenticacao.senha()));

        return usuarioRepository.save(usuario);
    }
}
