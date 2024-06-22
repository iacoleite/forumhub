package com.iaco.forumhub.controller;

import com.iaco.forumhub.domain.curso.Curso;
import com.iaco.forumhub.domain.curso.CursoDTO;
import com.iaco.forumhub.domain.curso.CursoDetalhes;
import com.iaco.forumhub.domain.curso.CursoRepositorio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/cursos")
public class CursoController {

        @Autowired
        private CursoRepositorio cursoRepositorio;

        @PostMapping
        @Transactional
        public ResponseEntity cadastrarCurso(@RequestBody @Valid CursoDTO cursoDTO, UriComponentsBuilder builder) {
            var curso = new Curso(cursoDTO);
            cursoRepositorio.save(curso);
            var uri = builder.path("/topicos/{id}").buildAndExpand(curso.getId()).toUri();
            var dto = new CursoDetalhes(curso);
            return ResponseEntity.created(uri).body(dto);
        }
}
