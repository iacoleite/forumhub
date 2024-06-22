package com.iaco.forumhub.domain.topico;

import com.iaco.forumhub.domain.ValidacaoException;
import com.iaco.forumhub.domain.curso.CursoRepositorio;
import com.iaco.forumhub.domain.usuario.UsuarioRepository;
import com.iaco.forumhub.infra.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico autenticarUsuario(String token, Long id) {
        var loggedUserToken = tokenService.getSubject((token.replace("Bearer ", "")));
        var usuario = usuarioRepository.findByEmail(loggedUserToken);
        var topico = topicoRepository.getReferenceById(id);

        if (topico == null) {
            throw new ValidacaoException("Tópico não encontrado");
        }

        if (usuario != topico.getAutor()) {
            throw new ValidacaoException("Não foi possível fazer as alterações");
        }
        return topico;
    }


}
