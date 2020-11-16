/**
 * 
 */
package br.com.fiapspring.dto;

import java.time.LocalDateTime;



/**
 * @author SaraRegina
 *
 */
public class CartaoCreateUpdateDTO {

	
	private Long numerocartao;
	private LocalDateTime datavalidade;
	private Long codigoIdentificador;

	/**
	 * 
	 */
	public CartaoCreateUpdateDTO() {
		// TODO Auto-generated constructor stub
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

}
