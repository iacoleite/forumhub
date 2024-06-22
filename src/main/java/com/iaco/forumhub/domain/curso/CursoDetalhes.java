package com.iaco.forumhub.domain.curso;

public record CursoDetalhes(Long id,
                            String nome,
                            Categoria categoria) {

    public CursoDetalhes(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
