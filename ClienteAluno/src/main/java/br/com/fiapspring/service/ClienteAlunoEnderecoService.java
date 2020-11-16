/**
 * 
 */
package br.com.fiapspring.service;

import java.util.List;

import org.springframework.stereotype.Service;


import br.com.fiapspring.dto.ClienteAlunoEnderecoCreateUpdateDTO;
import br.com.fiapspring.dto.ClienteAlunoEnderecoDTO;


/**
 * @author SaraRegina
 *
 */
@Service
public interface ClienteAlunoEnderecoService {

	public List<ClienteAlunoEnderecoDTO> findAll(Long id);

	public ClienteAlunoEnderecoDTO findById(long id);
	
	public ClienteAlunoEnderecoDTO create(ClienteAlunoEnderecoCreateUpdateDTO clienteAlunoEnderecoCreateUpdateDTO);
	
	public ClienteAlunoEnderecoDTO update(Long id, ClienteAlunoEnderecoCreateUpdateDTO clienteAlunoEnderecoCreateUpdateDTO);
	
	public void delete(Long id);
	
	public List<ClienteAlunoEnderecoDTO> buscaEnderecoPorIdCliente(Long idCliente);
	
	
}
