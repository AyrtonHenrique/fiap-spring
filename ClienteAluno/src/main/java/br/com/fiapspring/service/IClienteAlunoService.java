/**
 * 
 */
package br.com.fiapspring.service;

import java.util.List;

import br.com.fiap.persistence.entity.Cliente;
import br.com.fiapspring.entity.ClienteAluno;

/**
 * @author SaraRegina
 *
 */
public interface IClienteAlunoService {
	
	public List<ClienteAluno> findAll();
	
	public ClienteAluno addClienteAluno(ClienteAluno cliente);

	public ClienteAluno findById(long rm);
	
	public void deletarCliente(long rm);
	
	public void salvaCliente(ClienteAluno cliente);
	
	public ClienteAluno updateClienteAluno(ClienteAluno cliente);


}
