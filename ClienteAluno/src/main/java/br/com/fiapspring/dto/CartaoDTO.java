/**
 * 
 */
package br.com.fiapspring.dto;

import java.time.LocalDateTime;

import br.com.fiapspring.entity.Cartao;


/**
 * @author SaraRegina
 *
 */
public class CartaoDTO {

	private Long id;
	private Long numerocartao;
	private LocalDateTime datavalidade;
	private Long codigoIdentificador;
	private Long idcliente;

	
	
	
	/**
	 * 
	 */
	public CartaoDTO() {
	}


	/**
	 * 
	 */
	public CartaoDTO(Cartao cartao) {
		this.id = cartao.getIdCartao();
		this.numerocartao = cartao.getNumerocartao();
		this.datavalidade = cartao.getDatavalidade();
		this.idcliente = cartao.getClienteAluno().getIdCliente();
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
	public void setId(Long id) {
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
	 * @return the idcliente
	 */
	public Long getIdcliente() {
		return idcliente;
	}


	/**
	 * @param idcliente the idcliente to set
	 */
	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}

	

 
	
	

}
