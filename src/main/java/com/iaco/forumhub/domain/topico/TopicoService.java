package com.iaco.forumhub.domain.topico;

import com.iaco.forumhub.domain.DadosAtualizarTopico;
import com.iaco.forumhub.domain.ValidacaoException;
import com.iaco.forumhub.domain.curso.Curso;
import com.iaco.forumhub.domain.curso.CursoRepositorio;
import com.iaco.forumhub.domain.resposta.DadosNovaResposta;
import com.iaco.forumhub.domain.usuario.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepositorio cursoRepositorio;

    @Autowired
    private AuthenticationService authenticationService;

    public Curso getCurso(DadosNovoTopico dadosNovoTopico) {
        var curso = cursoRepositorio.getCurso(dadosNovoTopico.curso());
        if (curso == null) {
            throw new ValidacaoException("Curso não encontrado. Cadastre curso antes!");
        }
        return curso;
    }

    public DadosDetalhamentoTopico getDadosDetalhamentoTopicoAtualizado(Long id, DadosAtualizarTopico dadosAtualizarTopico, Authentication authentication) {
        var usuario = authenticationService.getUsuario(authentication);
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado");
        }
        if (!Objects.equals(topico.get().getAutor().getEmail(), usuario.getEmail())) {
            throw new ValidacaoException("Sem permissão para atualizar o tópico!");
        }
        topico.get().updateTopic(dadosAtualizarTopico);
        topicoRepository.save(topico.get());
        var dadosTopicoAtualizado = new DadosDetalhamentoTopico(topico.get());
        return dadosTopicoAtualizado;
    }

    public DadosDetalhamentoTopico getDadosDetalhamentoTopicoDeletado(Long id, Authentication authentication) {
        var usuario = authenticationService.getUsuario(authentication);
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado");
        }
        if (!Objects.equals(topico.get().getAutor().getEmail(), usuario.getEmail())) {
            throw new ValidacaoException("Sem permissão para atualizar o tópico!");
        }
        topico.get().deleteTopic();
        topicoRepository.save(topico.get());
        var dadosTopicoDeletado = new DadosDetalhamentoTopico(topico.get());
        return dadosTopicoDeletado;
    }

    public Optional<Topico> getTopico(DadosNovaResposta dadosNovaResposta) {
        var topico = topicoRepository.findById(dadosNovaResposta.topico());
        if (topico.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado.");
        }
        return topico;
    }
}

// Caso for excluir do DB o tópico:
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity deleteTopic(@PathVariable Long id, Authentication authentication) {
//        String email = authentication.getName();
//        var usuario = usuarioRepository.getReferenceByEmail(email);
//        Optional<Topico> topico = topicoRepository.findById(id);
//        if (topico.isEmpty()) {
//            throw new ValidacaoException("Tópico não encontrado");
//        }
//        if (!Objects.equals(topico.get().getAutor().getEmail(), usuario.getEmail())) {
//            throw new ValidacaoException("Sem permissão para atualizar o tópico!");
//        }
//        topicoRepository.deleteById(id);
//        return ResponseEntity.ok("DELETADO");
//    }

