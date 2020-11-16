/**
 * 
 */
package br.com.fiapspring.ClienteAluno;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiapspring.dto.ClienteAlunoCreateUpdateDTO;
import br.com.fiapspring.dto.ClienteAlunoDTO;
import br.com.fiapspring.entity.ClienteAluno;
import br.com.fiapspring.repository.ClienteAlunoRepository;
import br.com.fiapspring.service.ClienteAlunoService;

/**
 * @author SaraRegina
 *
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ClienteAlunoControllerTest {

	  @Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  private ObjectMapper objectMapper;

	  @Autowired
	  private ClienteAlunoRepository clienteAlunoRepository;
	  
	  @Autowired
	  private ClienteAlunoService clienteAlunoService;

	  @Test
	  void registraCliente() throws Exception {
	    ClienteAlunoCreateUpdateDTO clienteCreate = new ClienteAlunoCreateUpdateDTO(1234, "Sara", "36SCJ", "1234567", "123465-0", LocalDate.now() , true);
	    mockMvc.perform(post("/cliente")
	            .contentType("application/json")
	            .param("create", "true")
	            .content(objectMapper.writeValueAsString(clienteCreate.getNome())))
	            .andExpect(status().isOk());

	    Boolean foundAluno = clienteAlunoRepository.findAllByRm(1234).isPresent();
	    assertThat(foundAluno).isTrue();
	  }

	  
	  public void testGetClienteAlunoController() throws Exception {
		  this.mockMvc.perform(MockMvcRequestBuilders.get("/cliente")).andExpect(MockMvcResultMatchers.status().isOk());
	  }
	  
	  
	  public void testPUTClienteAlunoController() throws Exception {
		ClienteAluno clienteAluno = new ClienteAluno(1234, "Sara", "36SCJ", "1234567", "123465-0", LocalDate.now(), null, null , true);  
		ClienteAlunoDTO clienteAlunoDTO = new ClienteAlunoDTO(clienteAluno);  
		clienteAlunoDTO =  clienteAlunoService.updateAlunoToCliente(clienteAluno.getIdCliente());
	  	this.mockMvc.perform(MockMvcRequestBuilders.put("/cliente/" + clienteAluno.getIdCliente() )).andExpect(MockMvcResultMatchers.redirectedUrl("/cliente"));
	  }
	  
	  
	  public void testDeleteClienteAlunoController() throws Exception {
		ClienteAluno clienteAluno = new ClienteAluno(1234, "Sara", "36SCJ", "1234567", "123465-0", LocalDate.now(), null, null, true);  
	  	clienteAlunoService.delete(clienteAluno.getIdCliente());
	  	this.mockMvc.perform(MockMvcRequestBuilders.delete("/cliente/" + clienteAluno.getIdCliente() )).andExpect(MockMvcResultMatchers.redirectedUrl("/cliente"));
	  }

	   
	  public void testeGetDuscaClienteAlunoPorID() throws Exception {
		  ClienteAluno clienteAluno = new ClienteAluno(1234, "Sara", "36SCJ", "1234567", "123465-0", LocalDate.now(), null, null , true);  
		  this.mockMvc.perform(MockMvcRequestBuilders.get("/cliente/" + clienteAluno.getIdCliente() )).andExpect(MockMvcResultMatchers.status().isOk());
	  }
	  	
}