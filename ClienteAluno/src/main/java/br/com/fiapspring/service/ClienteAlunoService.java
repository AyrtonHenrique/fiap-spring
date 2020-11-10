/**
 * 
 */
package br.com.fiapspring.service;

import java.util.List;

import br.com.fiapspring.entity.ClienteAluno;


/**
 * @author SaraRegina
 *
 */
public interface ClienteAlunoService {
	
	public List<ClienteAluno> findAll();
	
	public ClienteAluno findById(Long rm);
	
	public ClienteAluno create(ClienteAluno clienteAluno);
	
	public ClienteAluno update(Long rm, ClienteAluno clienteAluno);
	
	public void delete(Long rm);
	
	


}
