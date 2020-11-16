/**
 * 
 */
package br.com.fiapspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiapspring.entity.Cartao;
import br.com.fiapspring.entity.ClienteAluno;

/**
 * @author SaraRegina
 *
 */
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
	
	 
	 List<Cartao> findByClienteAluno(ClienteAluno clienteAluno);
	  
	 Cartao findByNumerocartao(Long numerocartao);
	 
	 
    
}
