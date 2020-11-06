package br.com.fiapspring.entity;

import javax.persistence.Column;

import br.com.fiapspring.enums.TipoEndereco;

import javax.persistence.*;



@Entity
@Table(name = "ClienteAlunoEndereco")
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
	@JoinColumn(name = "id_cliente")
	private ClienteAluno cliente;

}
