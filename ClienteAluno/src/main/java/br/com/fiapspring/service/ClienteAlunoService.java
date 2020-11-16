/**
 * 
 */
package br.com.fiapspring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiapspring.dto.ClienteAlunoCreateUpdateDTO;
import br.com.fiapspring.dto.ClienteAlunoDTO;
import br.com.fiapspring.entity.ClienteAluno;



/**
 * @author SaraRegina
 *
 */
@Service
public interface ClienteAlunoService {
	
	public List<ClienteAlunoDTO> findAll(Long id);
	
	public ClienteAlunoDTO findById(long id);
	
	public ClienteAlunoDTO create(ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO);
	
	public ClienteAlunoDTO update(Long id, ClienteAlunoCreateUpdateDTO clienteAlunoCreateUpdateDTO);
	
	public void delete(Long id);
	
	public ClienteAlunoDTO getClienteAlunoByRm(Integer rm);
}




