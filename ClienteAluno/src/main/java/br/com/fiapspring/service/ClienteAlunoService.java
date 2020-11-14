/**
 * 
 */
package br.com.fiapspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import br.com.fiapspring.entity.ClienteAluno;


/**
 * @author SaraRegina
 *
 */
@Service
public interface ClienteAlunoService {
	
	public List<ClienteAluno> findAll();
	
	public List<ClienteAluno> findAllByIsclientecartaoIsTrue();
	
	public Optional<ClienteAluno> findById(long id);
	
	public ClienteAluno create(ClienteAluno clienteAluno);
	
	public ClienteAluno update(Integer rm, ClienteAluno clienteAluno);
	
	public void delete(Integer rm);
	
	public ClienteAluno getClienteAlunoByRm(Integer rm);
	

}
