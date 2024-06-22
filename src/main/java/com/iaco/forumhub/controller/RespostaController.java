package com.iaco.forumhub.controller;

import com.iaco.forumhub.domain.ValidacaoException;
import com.iaco.forumhub.domain.resposta.DadosDetalhamentoResposta;
import com.iaco.forumhub.domain.resposta.RespostaRepository;
import com.iaco.forumhub.domain.resposta.DadosNovaResposta;
import com.iaco.forumhub.domain.resposta.Resposta;
import com.iaco.forumhub.domain.topico.*;
import com.iaco.forumhub.domain.usuario.UsuarioRepository;
import com.iaco.forumhub.infra.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/respostas")
public class RespostaController {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity responderTopico(@RequestBody @Valid DadosNovaResposta dadosNovaResposta,
                                          UriComponentsBuilder builder, Authentication authentication) {
        String email = authentication.getName();
        System.out.println(email);
        var usuario = usuarioRepository.getReferenceByEmail(email);
        var topico = topicoRepository.findById(dadosNovaResposta.topico());
        if (topico.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado.");
        }

        var resposta = new Resposta(null, dadosNovaResposta.mensagem(), topico.get(), LocalDateTime.now(), usuario, false);

        respostaRepository.save(resposta);
        var uri = builder.path("/topicos/{id}").buildAndExpand(resposta.getId()).toUri();
        var dto = new DadosDetalhamentoResposta(resposta);
        return ResponseEntity.created(uri).body(dto);
    }
}
