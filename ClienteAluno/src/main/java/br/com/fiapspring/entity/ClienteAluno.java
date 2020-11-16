package br.com.fiapspring.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "tb_clientealuno")
public class ClienteAluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_clientealuno")
	private long idCliente;
	
	private Integer rm;
	private String rg;
	private String nome;
	private String turma;
	private String cpf;
	private LocalDate dataNascimento;
	private Boolean isCliente;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ClienteAlunoEndereco> clienteAlunoEnderecos = new HashSet<ClienteAlunoEndereco>(); 
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Cartao> cartoes  = new HashSet<Cartao>();
	
	/**
	 * 
	 */
	public ClienteAluno() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ClienteAluno(Integer rm, String nome, String turma, String cpf, String rg, LocalDate dataNascimento,
			Set<ClienteAlunoEndereco> clienteAlunoEnderecos, Set<Cartao> cartoes, Boolean isCliente) {
		super();
		this.rm = rm;
		this.nome = nome;
		this.turma = turma;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.cartoes = cartoes;
		this.clienteAlunoEnderecos = clienteAlunoEnderecos;
		this.isCliente = isCliente;
	}
	
	
	

	/**
	 * @return the id
	 */
	public long getIdCliente() {
		return idCliente;
	}


	/**
	 * @param id the id to set
	 */
	public void setIdCliente(long id) {
		this.idCliente = id;
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
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	/**
	 * @param dataNascimento the dataNascimento to set
	 */
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}




	/**
	 * @return the clienteAlunoEnderecos
	 */
	public Set<ClienteAlunoEndereco> getClienteAlunoEnderecos() {
		return clienteAlunoEnderecos;
	}


	/**
	 * @param clienteAlunoEnderecos the clienteAlunoEnderecos to set
	 */
	public void setClienteAlunoEnderecos(Set<ClienteAlunoEndereco> clienteAlunoEnderecos) {
		this.clienteAlunoEnderecos = clienteAlunoEnderecos;
	}



	public void addClienteAlunoEndereco(ClienteAlunoEndereco clienteAlunoEnderecos) {
		this.clienteAlunoEnderecos.add(clienteAlunoEnderecos);
	}
	
	/**
	 * @return the isCliente
	 */
	public Boolean getIsCliente() {
		return isCliente;
	}


	/**
	 * @param isCliente the isCliente to set
	 */
	public void setIsCliente(Boolean isCliente) {
		this.isCliente = isCliente;
	}

	


	/**
	 * @return the cartoes
	 */
	public Set<Cartao> getCartoes() {
		return cartoes;
	}


	/**
	 * @param cartoes the cartoes to set
	 */
	public void setCartoes(Set<Cartao> cartoes) {
		this.cartoes = cartoes;
	}


	@Override
	public String toString() {
		return "ClienteAluno [rm=" + rm + ", nome=" + nome + ", turma=" + turma + ", cpf=" + cpf + ", rg=" + rg
				+ ", dataNascimento=" + dataNascimento + ", enderecos=" + clienteAlunoEnderecos + ", getRm()=" + getRm()
				+ ", getNome()=" + getNome() + ", getTurma()=" + getTurma() + ", getCpf()=" + getCpf() + ", getRg()="
				+ getRg() + ", getDataNascimento()=" + getDataNascimento() + ", getEnderecos()=" + getClienteAlunoEnderecos()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(cpf, dataNascimento, clienteAlunoEnderecos, nome, rg, rm, turma);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ClienteAluno))
			return false;
		ClienteAluno other = (ClienteAluno) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(clienteAlunoEnderecos, other.clienteAlunoEnderecos) && Objects.equals(nome, other.nome)
				&& Objects.equals(rg, other.rg) && Objects.equals(rm, other.rm) && Objects.equals(turma, other.turma);
	}

	
	
	
	
	
}
