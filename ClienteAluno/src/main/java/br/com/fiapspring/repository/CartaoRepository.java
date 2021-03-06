/**
 * 
 */
package br.com.fiapspring.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiapspring.entity.Cartao;
import br.com.fiapspring.entity.ClienteAluno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author SaraRegina
 *
 */
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
	
	 
	 List<Cartao> findByClienteAluno(ClienteAluno clienteAluno);
	  
	 Cartao findByNumerocartao(Long numerocartao);

	@Query("from Cartao " +
			"where id_clientealuno = :idCliente")
	List<Cartao> buscaCartaoPorCliente(@Param("idCliente") Long idCliente);

	@Query("from Cartao " +
			"where numerocartao = :numeroCartao")
	List<Cartao> buscarCartaoPorNumero(@Param( value = "numeroCartao") Long numeroCartao);
}
