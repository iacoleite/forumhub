package com.iaco.forumhub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
//    Page<Topico> findAll(Pageable paginacao);
}
