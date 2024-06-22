package com.iaco.forumhub.domain.usuario;

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