package com.iaco.forumhub.domain.resposta;

import com.iaco.forumhub.domain.topico.Topico;
import com.iaco.forumhub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosNovaResposta(
        @NotBlank
        String mensagem,

        @NotNull
        @Valid
        Long topico


) {
}
