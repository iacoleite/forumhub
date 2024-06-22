package com.iaco.forumhub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosNovoTopico(
        @NotBlank (message = "")
        String titulo,

        @NotBlank (message = "")
        String mensagem,

        @NotBlank
        String curso
) {}
