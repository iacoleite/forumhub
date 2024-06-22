package com.iaco.forumhub.domain.usuario;

public record DadosCadastro(String email) {
    public DadosCadastro(Usuario usuario) {
        this(usuario.getEmail());
    }
}
