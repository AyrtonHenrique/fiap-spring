package br.com.fiapspring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiapspring.entity.ClienteAluno;


public interface ClienteAlunoRepository extends JpaRepository<ClienteAluno, Long> {
	
	List<ClienteAluno> findByIsclientecartao();

	void deleteById(ClienteAluno clienteAluno);
	

}
