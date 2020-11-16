/**
 * 
 */
package br.com.fiapspring.ClienteAluno;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiapspring.controller.ClienteAlunoController;
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

	  @Test
	  void registraCliente() throws Exception {
	    ClienteAlunoCreateUpdateDTO clienteCreate = new ClienteAlunoCreateUpdateDTO(1234, "Sara", "36SCJ", "1234567", "123465-0", LocalDate.now() , true);
	    mockMvc.perform(post("/clientealuno")
	            .contentType("application/json")
	            .param("create", "true")
	            .content(objectMapper.writeValueAsString(clienteCreate.getNome())))
	            .andExpect(status().isOk());

	    Boolean foundAluno = clienteAlunoRepository.findAllByRm(1234).isPresent();
	    assertThat(foundAluno).isTrue();
	  }


}