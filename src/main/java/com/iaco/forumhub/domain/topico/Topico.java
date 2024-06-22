package com.iaco.forumhub.domain.topico;


import com.iaco.forumhub.domain.DadosAtualizarTopico;
import com.iaco.forumhub.domain.curso.Curso;
import com.iaco.forumhub.domain.resposta.Resposta;
import com.iaco.forumhub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Resposta> respostas= new ArrayList<>();

    private Boolean ativo;

    public void updateTopic(DadosAtualizarTopico dados) {
        if (dados.titulo() != null && !dados.titulo().isEmpty()) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null && !dados.mensagem().isEmpty()) {
            this.mensagem = dados.mensagem();
        }
    }

    public void deleteTopic() {
        this.ativo = false;
    }

    public void deleteById(Long id) {

    }
}
