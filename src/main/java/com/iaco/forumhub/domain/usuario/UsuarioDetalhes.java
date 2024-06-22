package com.iaco.forumhub.domain.usuario;

import com.iaco.forumhub.domain.topico.Topico;

public record UsuarioDetalhes (
        Long id,
        String nome,
        String senha,
        String email
) {

    public UsuarioDetalhes(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getSenha(), usuario.getEmail());
    }
}