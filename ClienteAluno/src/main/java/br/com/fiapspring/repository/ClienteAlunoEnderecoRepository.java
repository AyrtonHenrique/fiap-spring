/**
 * 
 */
package br.com.fiapspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiapspring.entity.ClienteAlunoEndereco;
import org.springframework.data.repository.query.Param;

/**
 * @author SaraRegina
 *
 */
public interface ClienteAlunoEnderecoRepository extends JpaRepository<ClienteAlunoEndereco, Long> {
	
    @Query("from ClienteAlunoEndereco " +
            "where id_clientealuno = :idCliente")
    List<ClienteAlunoEndereco> buscaEnderecoPorCliente(@Param("idCliente") Long idCliente);

}
