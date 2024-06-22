package com.iaco.forumhub.domain.resposta;

import com.iaco.forumhub.domain.topico.Topico;
import com.iaco.forumhub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity(name = "Resposta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String mensagem;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "topico_id")
        private Topico topico;

        @Column(name = "data_criacao")
        private LocalDateTime dataCriacao;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "autor_id")
        private Usuario autor;

        private boolean solucao;

}

