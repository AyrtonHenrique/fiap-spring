/**
 * 
 */
package br.com.fiapspring.ClienteAluno;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiapspring.controller.ClienteAlunoEnderecoController;
import br.com.fiapspring.dto.ClienteAlunoEnderecoCreateUpdateDTO;
import br.com.fiapspring.dto.ClienteAlunoEnderecoDTO;
import br.com.fiapspring.entity.ClienteAlunoEndereco;
import br.com.fiapspring.enums.TipoEndereco;
import br.com.fiapspring.repository.ClienteAlunoEnderecoRepository;
import br.com.fiapspring.service.ClienteAlunoEnderecoService;

/**
 * @author SaraRegina
 *
 */
@WebMvcTest
class ClienteAlunoEnderecoTest {
	
		private ClienteAlunoEnderecoController clienteAlunoEnderecoController;
		
		@Autowired
		private MockMvc mockMvc;
		
		@Autowired
		private ObjectMapper objectMapper;
		
		@Autowired
		private ClienteAlunoEnderecoService clienteAlunoEnderecoService;
		
		@Autowired
		private ClienteAlunoEnderecoRepository clienteAlunoEnderecoRepository;


	  @Test
	  void registraEndereco() throws Exception {
	    ClienteAlunoEndereco endereco = new ClienteAlunoEndereco();
	    endereco.setId(1);
	    endereco.setCep("06000-123");
	    endereco.setCidade("Guarulhos");
	    endereco.setComplemento("Bloco 01");
	    endereco.setEstado("SP");
	    endereco.setLogradouro("Rua do Shopping");
	    endereco.setNumero(1234);
	    endereco.setTipoEndereco(TipoEndereco.RESIDENCIAL);
	    mockMvc.perform(post("/cliente/{idCliente}/endereco")
	            .contentType("application/json")
	            .param("create", "true")
	            .content(objectMapper.writeValueAsString(endereco.getCep())))
	            .andExpect(status().isOk());
	
	    Boolean foundAluno = clienteAlunoEnderecoRepository.findById(endereco.getId()).isPresent();
	    assertThat(foundAluno).isTrue();
	  }
	  
	  public void testGetClienteAlunoController() throws Exception {
		  this.mockMvc.perform(MockMvcRequestBuilders.get("/endereco")).andExpect(MockMvcResultMatchers.status().isOk());
	  }
	  
	  
	  public void testPUTClienteAlunoController() throws Exception {
		ClienteAlunoEndereco clienteAluno = new ClienteAlunoEndereco(1, "Estrada das Palmas", 123, "Casa", "09874-112", "Osasco", "SP", TipoEndereco.ENTREGA, null);  
		ClienteAlunoEnderecoCreateUpdateDTO  clienteAlunoEnderecoCreateUpdateDTO = new ClienteAlunoEnderecoCreateUpdateDTO();
		clienteAlunoEnderecoCreateUpdateDTO.setCep("09876-000");
		clienteAlunoEnderecoCreateUpdateDTO.setCidade("Barueri");
		clienteAlunoEnderecoCreateUpdateDTO.setComplemento("Casa");
		clienteAlunoEnderecoCreateUpdateDTO.setEstado("SP");
		clienteAlunoEnderecoCreateUpdateDTO.setLogradouro("Rua 3");
		clienteAlunoEnderecoCreateUpdateDTO.setNumero(1234);
		clienteAlunoEnderecoCreateUpdateDTO.setTipoEndereco(TipoEndereco.ENTREGA);
		ClienteAlunoEnderecoDTO clienteAlunoEnderecoDTO = new ClienteAlunoEnderecoDTO(clienteAluno);  
		clienteAlunoEnderecoDTO =  clienteAlunoEnderecoService.update(clienteAluno.getId(), clienteAluno.getId(), clienteAlunoEnderecoCreateUpdateDTO);
	  	this.mockMvc.perform(MockMvcRequestBuilders.put("/cliente/" + clienteAlunoEnderecoDTO.getIdCliente() + "/endereco" )).andExpect(MockMvcResultMatchers.redirectedUrl("/cliente/{idCliente}/endereco"));
	  }
	  
	  
	  public void testDeleteClienteAlunoController() throws Exception {
		  ClienteAlunoEndereco clienteAlunoEndereco = new ClienteAlunoEndereco(1, "Estrada das Palmas", 123, "Casa", "09874-112", "Osasco", "SP", TipoEndereco.ENTREGA, null);  
	  	clienteAlunoEnderecoService.delete(clienteAlunoEndereco.getClienteAluno().getIdCliente(),  clienteAlunoEndereco.getId());
	  	this.mockMvc.perform(MockMvcRequestBuilders.delete("/cliente/" + clienteAlunoEndereco.getClienteAluno().getIdCliente() + "/endereco" )).andExpect(MockMvcResultMatchers.redirectedUrl("cliente/{idCliente}/endereco"));
	  }

	  

	  
}
