package com.iaco.forumhub.domain.topico;

import com.iaco.forumhub.domain.curso.Curso;
import com.iaco.forumhub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record DadosNovoTopico(
        @NotBlank (message = "")
        String titulo,

        @NotBlank (message = "")
        String mensagem,

        @NotBlank
        String curso
)
//
//        @NotNull (message = "")
//        @Valid
//        Usuario autor,
//
//        @NotNull (message = "")
//        @Valid
//        Curso curso)
 {



}
