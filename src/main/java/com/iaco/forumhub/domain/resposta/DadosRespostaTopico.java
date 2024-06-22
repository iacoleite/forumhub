package com.iaco.forumhub.domain.resposta;

import java.time.LocalDateTime;

public record DadosRespostaTopico(Long id, String mensagem, String nomeAutor, LocalDateTime dataCriacao) {
    public DadosRespostaTopico(Resposta resposta) {
        this(resposta.getId(), resposta.getMensagem(), resposta.getAutor().getNome(), resposta.getDataCriacao());
    }
}
