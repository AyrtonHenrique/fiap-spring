/**
 * 
 */
package br.com.fiapspring.ClienteAluno;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiapspring.controller.CartaoController;
import br.com.fiapspring.dto.CartaoCreateUpdateDTO;
import br.com.fiapspring.dto.CartaoDTO;
import br.com.fiapspring.dto.ClienteAlunoCreateUpdateDTO;
import br.com.fiapspring.dto.ClienteAlunoDTO;
import br.com.fiapspring.entity.Cartao;
import br.com.fiapspring.entity.ClienteAluno;
import br.com.fiapspring.repository.CartaoRepository;
import br.com.fiapspring.service.CartaoService;

/**
 * @author SaraRegina
 *
 */
public class CartaoControllerTest {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testeGetCartao() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/cliente/{idCliente}/cartao")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public  void testRegistraCartao() throws Exception {
		    CartaoCreateUpdateDTO cartaoCreate = new CartaoCreateUpdateDTO();
		    cartaoCreate.setNumerocartao(123445678901l);
		    cartaoCreate.setDatavalidade(LocalDateTime.now());
		    cartaoCreate.setCodigoIdentificador(123l);
		    mockMvc.perform(post("cliente/{idCliente}/cartao")
		            .contentType("application/json")
		            .param("create", "true")
		            .content(objectMapper.writeValueAsString(cartaoCreate.getNumerocartao())))
		            .andExpect(status().isOk());

		    Boolean foundCartao = cartaoRepository.findById((110l)).isPresent();
		    assertThat(foundCartao).isTrue();
		  }
	
	  @Test
	  public void testGetCartaoController() throws Exception {
		  this.mockMvc.perform(MockMvcRequestBuilders.get("/cliente/{idCliente}/cartao")).andExpect(MockMvcResultMatchers.status().isOk());
	  }
	  
	  @Test
	  public void testPUTCartaoController() throws Exception {
		CartaoCreateUpdateDTO cartao = new CartaoCreateUpdateDTO();
		cartao.setNumerocartao(123456789012l);
		cartao.setDatavalidade(LocalDateTime.now());
		cartao.setCodigoIdentificador(231l);
		CartaoDTO cartaoDTO =  cartaoService.update(100l, 1145l ,cartao);
	  	this.mockMvc.perform(MockMvcRequestBuilders.put("/cliente/" + cartaoDTO.getIdcliente() +"/cartao/"+ cartaoDTO.getId() )).andExpect(MockMvcResultMatchers.redirectedUrl("/cliente/{idCliente}/cartao"));
	  }
	  
	  @Test
	  public void testDeleteClienteAlunoController() throws Exception {
		Cartao cartao = new Cartao();
		cartao.setIdCartao(110l);
		cartaoService.delete(112l , cartao.getIdCartao());
	  	this.mockMvc.perform(MockMvcRequestBuilders.delete("/cliente/" + cartao.getClienteAluno().getIdCliente() +"/cartao/"+ cartao.getIdCartao() )).andExpect(MockMvcResultMatchers.redirectedUrl("/cliente/{idCliente}/cartao"));
	  }


}
