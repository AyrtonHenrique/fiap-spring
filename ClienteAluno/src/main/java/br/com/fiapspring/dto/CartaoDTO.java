/**
 * 
 */
package br.com.fiapspring.dto;

import java.time.LocalDateTime;

import br.com.fiapspring.entity.Cartao;
import br.com.fiapspring.entity.ClienteAluno;

/**
 * @author SaraRegina
 *
 */
public class CartaoDTO {

	private Long id;
	private Long numerocartao;
	private LocalDateTime datavalidade;
	private Long codigoIdentificador;

	private ClienteAluno clienteAluno ; 

	
	/**
	 * 
	 */
	public CartaoDTO(Cartao cartao) {
		this.id = cartao.getId();
		this.numerocartao = cartao.getNumerocartao();
		this.datavalidade = cartao.getDatavalidade();
		this.clienteAluno = cartao.getClienteAluno();
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * @return the numerocartao
	 */
	public Long getNumerocartao() {
		return numerocartao;
	}


	/**
	 * @param numerocartao the numerocartao to set
	 */
	public void setNumerocartao(Long numerocartao) {
		this.numerocartao = numerocartao;
	}


	/**
	 * @return the datavalidade
	 */
	public LocalDateTime getDatavalidade() {
		return datavalidade;
	}


	/**
	 * @param datavalidade the datavalidade to set
	 */
	public void setDatavalidade(LocalDateTime datavalidade) {
		this.datavalidade = datavalidade;
	}


	/**
	 * @return the codigoIdentificador
	 */
	public Long getCodigoIdentificador() {
		return codigoIdentificador;
	}


	/**
	 * @param codigoIdentificador the codigoIdentificador to set
	 */
	public void setCodigoIdentificador(Long codigoIdentificador) {
		this.codigoIdentificador = codigoIdentificador;
	}


	/**
	 * @return the clienteAluno
	 */
	public ClienteAluno getClienteAluno() {
		return clienteAluno;
	}


	/**
	 * @param clienteAluno the clienteAluno to set
	 */
	public void setClienteAluno(ClienteAluno clienteAluno) {
		this.clienteAluno = clienteAluno;
	}
	
	

}
