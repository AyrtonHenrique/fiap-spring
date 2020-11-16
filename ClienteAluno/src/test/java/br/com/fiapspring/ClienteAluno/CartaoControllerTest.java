/**
 * 
 */
package br.com.fiapspring.ClienteAluno;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.fiapspring.controller.CartaoController;

/**
 * @author SaraRegina
 *
 */
public class CartaoControllerTest {
	
	private CartaoController cartaoController;

	private MockMvc mockMvc;

	@Before(value = "")
	public void setUp() {
	    mockMvc = MockMvcBuilders.standaloneSetup(cartaoController).build();
	}

	@Test
	public void testeListagemEendereco() throws Exception {
	    ResultActions response = mockMvc.perform(
	                get("/clienteluno-app/cartao")
	                        .contentType(MediaType.TEXT_HTML)
	                        .header("CartaoCreateUpdateDTO", "CartaoCreateUpdateDTO"));

	    response.andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	            .andExpect(content().string("CartaoDTO"));
	           
	}
}
