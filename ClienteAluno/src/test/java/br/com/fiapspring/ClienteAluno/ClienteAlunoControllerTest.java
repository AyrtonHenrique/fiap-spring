/**
 * 
 */
package br.com.fiapspring.ClienteAluno;

import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import br.com.fiapspring.controller.ClienteAlunoController;
import br.com.fiapspring.entity.ClienteAluno;
import br.com.fiapspring.repository.ClienteAlunoRepository;
import br.com.fiapspring.service.ClienteAlunoService;


/**
 * @author SaraRegina
 *
 */

class ClienteAlunoControllerTest extends ClienteAlunoApplicationTests  {

	private MockMvc mockMvc;
	
	@Autowired
	private ClienteAlunoController clienteAlunoController;
	
	@Autowired
	private ClienteAlunoService clienteAlunoService;  
	
	@Autowired
	private ClienteAlunoRepository clienteAlunoRepository;
	
	@Before
	public void setUp() {
	this.mockMvc = MockMvcBuilders.standaloneSetup(clienteAlunoController).build();
	}
	
	@Test
	@Ignore
	public void testGETListaTodos() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/listarTodos")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testPOSTSaveClienteAlunoController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
			.param("cpf", "12345670")
			.param("dataNascimento", "1986-09-13")
			.param("isCliente", "true")
			.param("nome", "Sara Pires")
			.param("rg", "12345678")
			.param("rm", "098765")
			.param("turma", "36SCJ")
			).andExpect(MockMvcResultMatchers.redirectedUrl("/cliente"));
	}	

	@Test
	@Ignore
	public void testPUTClienteAlunoController() throws Exception {
		ClienteAluno clienteAluno = (ClienteAluno) clienteAlunoRepository.save(new ClienteAluno());
		this.mockMvc.perform(MockMvcRequestBuilders.put("/cliente/" + clienteAluno.getIdCliente())).andExpect(MockMvcResultMatchers.redirectedUrl("/salarios"));
	}
		

}