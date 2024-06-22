package com.iaco.forumhub.controller;

import com.iaco.forumhub.domain.ValidacaoException;
import com.iaco.forumhub.domain.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrar")
public class RegistrarUsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity registrarNovoUsuario(@RequestBody @Valid UsuarioDetalhes dados) {
        try {
            Usuario novoUsuario = service.criarNovoUsuario(dados);
            return ResponseEntity.status(HttpStatus.CREATED).body(new DadosCadastro(novoUsuario));
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
