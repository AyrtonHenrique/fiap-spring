package br.com.fiapspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.fiapspring.entity.ClienteAluno;


public interface ClienteAlunoRepository extends JpaRepository<ClienteAluno, Long> {
	
	void deleteById(ClienteAluno clienteAluno);

	ClienteAluno findAllByRm(Integer rm);
	
	List<ClienteAluno> findAllByIsclientecartaoIsTrue();

}
