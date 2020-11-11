package br.com.fiapspring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiapspring.entity.ClienteAluno;


public interface ClienteAlunoRepository extends JpaRepository<ClienteAluno, Long> {
	
	void deleteById(ClienteAluno clienteAluno);
	
	

}
