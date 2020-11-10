package br.com.fiapspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.fiapspring.entity.ClienteAluno;

public interface ClienteAlunoRepository extends JpaRepository<ClienteAluno, Long> {
	
	Optional<ClienteAluno> findByRm(Long id);

}
