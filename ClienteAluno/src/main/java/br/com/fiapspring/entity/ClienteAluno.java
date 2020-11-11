package br.com.fiapspring.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "tb_clientealuno")
public class ClienteAluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_clientealuno")
	private long id;
	
	private Integer rm;
	private String nome;
	private String turma;
	private String cpf;
	private String rg;
	private LocalDateTime dataNascimento;
	private Boolean isclientecartao;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private static Set<ClienteAlunoEndereco> enderecos = new HashSet<ClienteAlunoEndereco>(); 
	
	@OneToOne()
	private Cartao cartao;
	
	/**
	 * 
	 */
	public ClienteAluno() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ClienteAluno(Integer rm, String nome, String turma, String cpf, String rg, LocalDateTime dataNascimento,
			Set<ClienteAlunoEndereco> enderecos, Boolean isclientecartao) {
		super();
		this.rm = rm;
		this.nome = nome;
		this.turma = turma;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.enderecos = enderecos;
		this.isclientecartao = isclientecartao;
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
	 * @return the rm
	 */
	public Integer getRm() {
		return rm;
	}


	/**
	 * @param rm the rm to set
	 */
	public void setRm(Integer rm) {
		this.rm = rm;
	}


	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	/**
	 * @return the turma
	 */
	public String getTurma() {
		return turma;
	}


	/**
	 * @param turma the turma to set
	 */
	public void setTurma(String turma) {
		this.turma = turma;
	}


	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}


	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	/**
	 * @return the rg
	 */
	public String getRg() {
		return rg;
	}


	/**
	 * @param rg the rg to set
	 */
	public void setRg(String rg) {
		this.rg = rg;
	}


	/**
	 * @return the dataNascimento
	 */
	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}


	/**
	 * @param dataNascimento the dataNascimento to set
	 */
	public void setDataNascimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	/**
	 * @return the enderecos
	 */
	public static Set<ClienteAlunoEndereco> getEnderecos() {
		return enderecos;
	}


	/**
	 * @param enderecos the enderecos to set
	 */
	public void setEnderecos(Set<ClienteAlunoEndereco> enderecos) {
		this.enderecos = enderecos;
	}

	public void addEndereco(ClienteAlunoEndereco enderecos) {
		this.enderecos.add(enderecos);
	}
	
	
	/**
	 * @return the isclientecartao
	 */
	public Boolean getIsclientecartao() {
		return isclientecartao;
	}


	/**
	 * @param isclientecartao the isclientecartao to set
	 */
	public void setIsclientecartao(Boolean isclientecartao) {
		this.isclientecartao = isclientecartao;
	}

	
	

	/**
	 * @return the cartao
	 */
	public Cartao getCartao() {
		return cartao;
	}


	/**
	 * @param cartao the cartao to set
	 */
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}


	@Override
	public String toString() {
		return "ClienteAluno [rm=" + rm + ", nome=" + nome + ", turma=" + turma + ", cpf=" + cpf + ", rg=" + rg
				+ ", dataNascimento=" + dataNascimento + ", enderecos=" + enderecos + ", getRm()=" + getRm()
				+ ", getNome()=" + getNome() + ", getTurma()=" + getTurma() + ", getCpf()=" + getCpf() + ", getRg()="
				+ getRg() + ", getDataNascimento()=" + getDataNascimento() + ", getEnderecos()=" + getEnderecos()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(cpf, dataNascimento, enderecos, nome, rg, rm, turma);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ClienteAluno))
			return false;
		ClienteAluno other = (ClienteAluno) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(enderecos, other.enderecos) && Objects.equals(nome, other.nome)
				&& Objects.equals(rg, other.rg) && Objects.equals(rm, other.rm) && Objects.equals(turma, other.turma);
	}

	
	
	
	
	
}
