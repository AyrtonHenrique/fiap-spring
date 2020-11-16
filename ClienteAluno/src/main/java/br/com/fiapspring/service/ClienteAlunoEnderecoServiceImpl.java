/**
 * 
 */
package br.com.fiapspring.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.fiapspring.controller.ClienteAlunoController;
import br.com.fiapspring.dto.ClienteAlunoEnderecoCreateUpdateDTO;
import br.com.fiapspring.dto.ClienteAlunoEnderecoDTO;
import br.com.fiapspring.entity.ClienteAlunoEndereco;
import br.com.fiapspring.repository.ClienteAlunoEnderecoRepository;

/**
 * @author SaraRegina
 *
 */
@Service
public class ClienteAlunoEnderecoServiceImpl implements ClienteAlunoEnderecoService {

	private final Logger logger = LoggerFactory.getLogger(ClienteAlunoController.class);

	private ClienteAlunoEnderecoRepository clienteAlunoEnderecoRepository;

	public ClienteAlunoEnderecoServiceImpl(ClienteAlunoEnderecoRepository clienteAlunoEnderecoRepository) {
		this.clienteAlunoEnderecoRepository = clienteAlunoEnderecoRepository;
	}

	@Override
	public ClienteAlunoEnderecoDTO create(ClienteAlunoEnderecoCreateUpdateDTO clienteAlunoEnderecoCreateUpdateDTO) {
		ClienteAlunoEndereco clienteAlunoEndereco = new ClienteAlunoEndereco();
		
		clienteAlunoEndereco.setCep(clienteAlunoEnderecoCreateUpdateDTO.getCep());
		clienteAlunoEndereco.setCidade(clienteAlunoEnderecoCreateUpdateDTO.getCidade());
		clienteAlunoEndereco.setEstado(clienteAlunoEnderecoCreateUpdateDTO.getEstado());
		clienteAlunoEndereco.setLogradouro(clienteAlunoEnderecoCreateUpdateDTO.getLogradouro());
		clienteAlunoEndereco.setNumero(clienteAlunoEnderecoCreateUpdateDTO.getNumero());
		clienteAlunoEndereco.setComplemento(clienteAlunoEnderecoCreateUpdateDTO.getComplemento());
		clienteAlunoEndereco.setTipoEndereco(clienteAlunoEnderecoCreateUpdateDTO.getTipoEndereco());
		ClienteAlunoEndereco salvarEndereco = clienteAlunoEnderecoRepository.save(clienteAlunoEndereco);
		
		return new ClienteAlunoEnderecoDTO(salvarEndereco);
	}

	@Override
	public ClienteAlunoEnderecoDTO update(Long id, ClienteAlunoEnderecoCreateUpdateDTO clienteAlunoEnderecoCreateUpdateDTO) {
		ClienteAlunoEndereco clienteAlunoEndereco = getClienteAlunoEndereco(id).get();
		
		clienteAlunoEndereco.setCep(clienteAlunoEnderecoCreateUpdateDTO.getCep());
		clienteAlunoEndereco.setCidade(clienteAlunoEnderecoCreateUpdateDTO.getCidade());
		clienteAlunoEndereco.setEstado(clienteAlunoEnderecoCreateUpdateDTO.getEstado());
		clienteAlunoEndereco.setLogradouro(clienteAlunoEnderecoCreateUpdateDTO.getLogradouro());
		clienteAlunoEndereco.setNumero(clienteAlunoEnderecoCreateUpdateDTO.getNumero());
		clienteAlunoEndereco.setComplemento(clienteAlunoEnderecoCreateUpdateDTO.getComplemento());
		clienteAlunoEndereco.setTipoEndereco(clienteAlunoEnderecoCreateUpdateDTO.getTipoEndereco());
		
		ClienteAlunoEndereco salvarEndereco = clienteAlunoEnderecoRepository.save(clienteAlunoEndereco);
	
		logger.info("Dados do Cliente atualizados");
		return new ClienteAlunoEnderecoDTO (salvarEndereco);
	}

	@Override
	public void delete(Long id) {
		ClienteAlunoEndereco clienteEndereco = getClienteAlunoEndereco(id).get();
		logger.info("Dados do Cliente removidos");
		clienteAlunoEnderecoRepository.deleteById(clienteEndereco.getId());
	}


	private Optional<ClienteAlunoEndereco> getClienteAlunoEndereco(Long id) {
		return clienteAlunoEnderecoRepository.findById(id);
	}

	@Override
	public List<ClienteAlunoEnderecoDTO> findAll(Long id) {
		
	 return clienteAlunoEnderecoRepository.findById(id)
                .stream()
                .map(clienteAluno -> new ClienteAlunoEnderecoDTO(clienteAluno))
                .collect(Collectors.toList());
	}

	@Override
	public ClienteAlunoEnderecoDTO findById(long id) {
		ClienteAlunoEndereco cliAluEndereco = getClienteAlunoEndereco(id).get();
		return new ClienteAlunoEnderecoDTO(cliAluEndereco);
	}
	
	@Override
	public List<ClienteAlunoEnderecoDTO> buscaEnderecoPorIdCliente(Long idCliente) {
		return clienteAlunoEnderecoRepository.buscaEnderecoPorCliente(idCliente)
				.stream()
				.map(clienteAlunoEndereco -> new ClienteAlunoEnderecoDTO(clienteAlunoEndereco))
				.collect(Collectors.toList());
		
	}  
}

