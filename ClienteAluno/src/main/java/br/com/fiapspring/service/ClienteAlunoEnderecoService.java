/**
 * 
 */
package br.com.fiapspring.service;

import java.util.List;

import br.com.fiapspring.entity.ClienteAlunoEndereco;
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

	public ClienteAlunoEnderecoDTO findById(Long idCliente, Long idEndereco);
	
	public ClienteAlunoEndereco create(Long idCliente, ClienteAlunoEnderecoCreateUpdateDTO clienteAlunoEnderecoCreateUpdateDTO);

	public void delete(Long idCliente, Long idEndereco);
	
	public List<ClienteAlunoEnderecoDTO> buscaEnderecoPorIdCliente(Long idCliente);


	public ClienteAlunoEndereco update(Long idCliente, Long idEndereco, ClienteAlunoEnderecoCreateUpdateDTO clienteAlunoEnderecoCreateUpdateDTO);
}
