package com.iaco.forumhub.domain.resposta;

import com.iaco.forumhub.domain.topico.DadosDetalhamentoTopico;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(Long id,
                                        String mensagem,
                                        LocalDateTime dataCriacao) {

    public DadosDetalhamentoResposta(Resposta resposta) {
        this(resposta.getId(), resposta.getMensagem(), resposta.getDataCriacao());
    }
}
