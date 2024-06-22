package com.iaco.forumhub.controller;

import com.iaco.forumhub.domain.DadosAtualizarTopico;
import com.iaco.forumhub.domain.ValidacaoException;
import com.iaco.forumhub.domain.curso.CursoRepositorio;
import com.iaco.forumhub.domain.topico.*;
import com.iaco.forumhub.domain.usuario.UsuarioRepository;
import com.iaco.forumhub.infra.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

//@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepositorio cursoRepositorio;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity criarNovoTopico(@RequestBody @Valid DadosNovoTopico dadosNovoTopico,
                                          UriComponentsBuilder builder, Authentication authentication) {
        String email = authentication.getName();
        var usuario = usuarioRepository.getReferenceByEmail(email);
        var curso = cursoRepositorio.getCurso(dadosNovoTopico.curso());
        if (curso == null) {
            throw new ValidacaoException("Curso não encontrado. Cadastre curso antes!");
        }

        var topico = new Topico(null, dadosNovoTopico.titulo(), dadosNovoTopico.mensagem(), LocalDateTime.now(), Status.OPEN, usuario, curso, null, true);
        topicoRepository.save(topico);
        var uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        var dto = new DadosDetalhamentoTopico(topico);
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity getAllTopicos(@PageableDefault(size = 2, sort={"dataCriacao"},
            direction = Sort.Direction.DESC)Pageable paginacao, PagedResourcesAssembler assembler) {
        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);

        return ResponseEntity.ok(assembler.toModel(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity getTopico(@PathVariable Long id) {
        var topico  = topicoRepository.getReferenceById(id);
        return  ResponseEntity.ok(new DadosTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateTopic(@PathVariable Long id, @RequestBody @Valid DadosAtualizarTopico dadosAtualizarTopico,
                                    Authentication authentication) {
        String email = authentication.getName();
        var usuario = usuarioRepository.getReferenceByEmail(email);
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado");
        }
        if (!Objects.equals(topico.get().getAutor().getEmail(), usuario.getEmail())) {
            throw new ValidacaoException("Sem permissão para atualizar o tópico!");
        }
        topico.get().updateTopic(dadosAtualizarTopico);
        topicoRepository.save(topico.get());
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico.get()));
    }

    // Optei foi fazer a exclusao logica, na sequencia vem o codigo comentado para excluir do DB
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id, Authentication authentication) {
        String email = authentication.getName();
        var usuario = usuarioRepository.getReferenceByEmail(email);
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado");
        }
        if (!Objects.equals(topico.get().getAutor().getEmail(), usuario.getEmail())) {
            throw new ValidacaoException("Sem permissão para atualizar o tópico!");
        }
        topico.get().deleteTopic();
        topicoRepository.save(topico.get());
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico.get()));
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
}
