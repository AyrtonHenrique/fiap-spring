/**
 * 
 */
package br.com.fiapspring.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;


/**
 * @author SaraRegina
 *
 */
@Entity
@Table(name = "tb_cartao")
public class Cartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idCartao;
	private Long numerocartao;
	private LocalDate datavalidade;
	private Long codigoIdentificador;
	
	@ManyToOne()
	@JoinColumn(name = "id_clientealuno")
	private ClienteAluno clienteAluno ; 
	
	/**
	 * 
	 */
	public Cartao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public long getIdCartao() {
		return idCartao;
	}

	/**
	 * @param id the id to set
	 */
	public void setIdCartao(long idCartao) {
		this.idCartao = idCartao;
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
	public LocalDate getDatavalidade() {
		return datavalidade;
	}

	/**
	 * @param datavalidade the datavalidade to set
	 */
	public void setDatavalidade(LocalDate datavalidade) {
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
