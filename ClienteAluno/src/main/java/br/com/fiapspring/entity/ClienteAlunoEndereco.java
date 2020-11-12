package br.com.fiapspring.entity;

import javax.persistence.Column;

import br.com.fiapspring.enums.TipoEndereco;

import javax.persistence.*;



@Entity
@Table(name = "tb_clientealunoendereco")
public class ClienteAlunoEndereco {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_endereco")
	private long id;
	@Column(name = "nm_logradouro")
	private String logradouro;
	@Column(name = "nr_numero")
	private Integer numero;
	@Column(name = "ds_complemento")
	private String complemento;
	@Column(name = "nr_cep")
	private String cep;
	@Column(name = "ds_cidade")
	private String cidade;
	@Column(name = "ds_estado")
	private String estado;
	@Column(name = "tipo_endereco")
	private TipoEndereco tipoEndereco;
	
	@ManyToOne()
	@JoinColumn(name = "id")
	private ClienteAluno clienteAluno;

	public ClienteAlunoEndereco() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteAlunoEndereco(long id, String logradouro, Integer numero, String complemento, String cep,
			String cidade, String estado, TipoEndereco tipoEndereco, ClienteAluno clienteAluno) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.tipoEndereco = tipoEndereco;
		this.clienteAluno = clienteAluno;
	}
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public ClienteAluno getClienteAluno() {
		return clienteAluno;
	}

	public void setClienteAluno(ClienteAluno clienteAluno) {
		this.clienteAluno = clienteAluno;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ClienteAlunoEndereco [id=" + id + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento="
				+ complemento + ", cep=" + cep + ", cidade=" + cidade + ", estado=" + estado + ", tipoEndereco="
				+ tipoEndereco + ", clienteAluno=" + clienteAluno + "]";
	}
	
	

}
