package com.iaco.forumhub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {

    @Query("select c from Curso c where lower(c.nome) = lower(:curso)")
    Curso getCurso(String curso);
}
