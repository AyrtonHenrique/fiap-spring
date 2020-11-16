/**
 * 
 */
package br.com.fiapspring.ClienteAluno;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.fiapspring.controller.ClienteAlunoEnderecoController;

/**
 * @author SaraRegina
 *
 */
@WebMvcTest
public class ClienteAlunoEnderecoTest {
	
		private ClienteAlunoEnderecoController clienteAlunoEnderecoController;
		

		private MockMvc mockMvc;

		@Before(value = "")
		public void setUp() {
		    mockMvc = MockMvcBuilders.standaloneSetup(clienteAlunoEnderecoController).build();
		}

		@Test
		public void testeListagemEendereco() throws Exception {
		    ResultActions response = mockMvc.perform(
		                get("/clienteluno-app/endereco")
		                        .contentType(MediaType.TEXT_HTML)
		                        .header("ClienteAlunoEnderecoDTO", "ClienteAlunoEnderecoDTO"));

		    response.andExpect(status().isOk())
		            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		            .andExpect(content().string("ClienteAlunoEnderecoDTO"));
		           
		}
}
