package com.iaco.forumhub.controller;

import com.iaco.forumhub.domain.topico.DadosAtualizarTopico;
import com.iaco.forumhub.domain.topico.*;
import com.iaco.forumhub.domain.usuario.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    @Transactional
    public ResponseEntity criarNovoTopico(@RequestBody @Valid DadosNovoTopico dadosNovoTopico,
                                          UriComponentsBuilder builder, Authentication authentication) {
        var usuario = authenticationService.getUsuario(authentication);
        var curso = topicoService.getCurso(dadosNovoTopico);

        var topico = new Topico(null, dadosNovoTopico.titulo(), dadosNovoTopico.mensagem(), LocalDateTime.now(), Status.OPEN, usuario, curso, null, true);
        topicoRepository.save(topico);
        var dto = new DadosDetalhamentoTopico(topico);
        var uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity getAllTopicos(@PageableDefault(size = 2, sort={"dataCriacao"},
            direction = Sort.Direction.DESC)Pageable paginacao, PagedResourcesAssembler assembler) {
        var page = topicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemTopico::new);

        // to get all topics (if I want to implement a admin_role this could be useful)
        //        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);

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
        var dadosTopicoAtualizado = topicoService.getDadosDetalhamentoTopicoAtualizado(id, dadosAtualizarTopico, authentication);
        return ResponseEntity.ok(dadosTopicoAtualizado);
    }

    // Optei foi fazer a exclusao logica, na sequencia vem o codigo comentado para excluir do DB
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id, Authentication authentication) {
        var dadosTopicoDeletado = topicoService.getDadosDetalhamentoTopicoDeletado(id, authentication);
        return ResponseEntity.ok(dadosTopicoDeletado);
    }

}
