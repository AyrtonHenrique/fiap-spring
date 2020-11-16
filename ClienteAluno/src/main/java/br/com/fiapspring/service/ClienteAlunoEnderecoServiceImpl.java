/**
 * 
 */
package br.com.fiapspring.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.fiapspring.entity.ClienteAluno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.fiapspring.controller.ClienteAlunoController;
import br.com.fiapspring.dto.ClienteAlunoEnderecoCreateUpdateDTO;
import br.com.fiapspring.dto.ClienteAlunoEnderecoDTO;
import br.com.fiapspring.entity.ClienteAlunoEndereco;
import br.com.fiapspring.repository.ClienteAlunoEnderecoRepository;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author SaraRegina
 *
 */
@Service
public class ClienteAlunoEnderecoServiceImpl implements ClienteAlunoEnderecoService {

	private final Logger logger = LoggerFactory.getLogger(ClienteAlunoController.class);
	private ClienteAlunoService clienteAlunoService;
	private ClienteAlunoEnderecoRepository clienteAlunoEnderecoRepository;

	public ClienteAlunoEnderecoServiceImpl(ClienteAlunoService clienteAlunoService,
										   ClienteAlunoEnderecoRepository clienteAlunoEnderecoRepository) {
		this.clienteAlunoEnderecoRepository = clienteAlunoEnderecoRepository;
		this.clienteAlunoService = clienteAlunoService;
	}

	@Override
	public ClienteAlunoEnderecoDTO create(Long idCliente,ClienteAlunoEnderecoCreateUpdateDTO clienteAlunoEnderecoCreateUpdateDTO) {
		ClienteAluno clienteAluno = clienteAlunoService.getAluno(idCliente);

		ClienteAlunoEndereco clienteAlunoEndereco = new ClienteAlunoEndereco();

		clienteAlunoEndereco.setClienteAluno(clienteAluno);
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
	public ClienteAlunoEnderecoDTO update(Long idCliente, Long idEndereco, ClienteAlunoEnderecoCreateUpdateDTO clienteAlunoEnderecoCreateUpdateDTO) {
		ClienteAlunoEndereco clienteAlunoEndereco = findEnderecoAlunoById(idCliente,idEndereco);

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
	public void delete(Long idCliente, Long idEndereco) {
		ClienteAlunoEndereco clienteEndereco = findEnderecoAlunoById(idCliente, idEndereco);
		clienteAlunoEnderecoRepository.deleteById(clienteEndereco.getId());
	}

	@Override
	public List<ClienteAlunoEnderecoDTO> findAll(Long id) {
		
	 return clienteAlunoEnderecoRepository.findById(id)
                .stream()
                .map(clienteAluno -> new ClienteAlunoEnderecoDTO(clienteAluno))
                .collect(Collectors.toList());
	}

	@Override
	public ClienteAlunoEnderecoDTO findById(Long idCliente, Long idEndereco) {
		ClienteAlunoEndereco cliAluEndereco =  findEnderecoAlunoById(idCliente, idEndereco);
		return new ClienteAlunoEnderecoDTO(cliAluEndereco);
	}
	
	@Override
	public List<ClienteAlunoEnderecoDTO> buscaEnderecoPorIdCliente(Long idCliente) {
		return clienteAlunoEnderecoRepository.buscaEnderecoPorCliente(idCliente)
				.stream()
				.map(clienteAlunoEndereco -> new ClienteAlunoEnderecoDTO(clienteAlunoEndereco))
				.collect(Collectors.toList());
		
	}

	private ClienteAlunoEndereco findEnderecoAlunoById(Long idCliente, Long idEndereco ){
		return clienteAlunoEnderecoRepository.findById(idEndereco)
				.filter(clienteAlunoEndereco -> clienteAlunoEndereco.getClienteAluno().getIdCliente() == idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}


}

