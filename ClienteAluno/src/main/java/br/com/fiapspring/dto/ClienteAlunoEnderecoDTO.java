/**
 * 
 */
package br.com.fiapspring.dto;

import br.com.fiapspring.entity.ClienteAlunoEndereco;
import br.com.fiapspring.enums.TipoEndereco;

/**
 * @author SaraRegina
 *
 */
public class ClienteAlunoEnderecoDTO {
	
	
	private Long id;
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String cep;
	private String cidade;
	private String estado;
	private TipoEndereco tipoEndereco;
	private Long idCliente;

	/**
	 * 
	 */
	public ClienteAlunoEnderecoDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param logradouro
	 * @param numero
	 * @param complemento
	 * @param cep
	 * @param cidade
	 * @param estado
	 * @param tipoEndereco
	 */
	public ClienteAlunoEnderecoDTO(ClienteAlunoEndereco clienteAlunoEndereco) {
		super();
		this.id = clienteAlunoEndereco.getId();
		this.logradouro = clienteAlunoEndereco.getLogradouro();
		this.numero = clienteAlunoEndereco.getNumero();
		this.complemento = clienteAlunoEndereco.getComplemento();
		this.cep = clienteAlunoEndereco.getCep();
		this.cidade = clienteAlunoEndereco.getCidade();
		this.estado = clienteAlunoEndereco.getEstado();
		this.tipoEndereco = clienteAlunoEndereco.getTipoEndereco();
		this.idCliente = clienteAlunoEndereco.getClienteAluno().getIdCliente();
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
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * @param logradouro the logradouro to set
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @param complemento the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the tipoEndereco
	 */
	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	/**
	 * @param tipoEndereco the tipoEndereco to set
	 */
	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	/**
	 * @return the idCliente
	 */
	public Long getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	
	

}
