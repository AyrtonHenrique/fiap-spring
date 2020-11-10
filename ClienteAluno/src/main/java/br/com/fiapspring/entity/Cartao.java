/**
 * 
 */
package br.com.fiapspring.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
	private long id;
	private Long numerocartao;
	private LocalDateTime datavalidade;
	private Long codigoIdentificador;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ClienteAluno> cliente = new HashSet<ClienteAluno>(); 
	
	/**
	 * 
	 */
	public Cartao() {
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @param id
	 * @param numerocartao
	 * @param datavalidade
	 * @param codigoIdentificador
	 * @param cliente
	 */
	public Cartao(long id, Long numerocartao, LocalDateTime datavalidade, Long codigoIdentificador,
			Set<ClienteAluno> cliente) {
		super();
		this.id = id;
		this.numerocartao = numerocartao;
		this.datavalidade = datavalidade;
		this.codigoIdentificador = codigoIdentificador;
		this.cliente = cliente;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
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
	 * @return the cliente
	 */
	public Set<ClienteAluno> getCliente() {
		return cliente;
	}



	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Set<ClienteAluno> cliente) {
		this.cliente = cliente;
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
