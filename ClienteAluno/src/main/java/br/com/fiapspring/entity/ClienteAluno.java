package br.com.fiapspring.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;



@Entity
public class ClienteAluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rm")
	private Integer rm;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "turma")
	private String turma;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "rg")
	private String rg;
	
	@Column(name = "dataNacimento")
	private LocalDateTime dataNascimento;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ClienteAlunoEndereco> enderecos = new HashSet<ClienteAlunoEndereco>(); 

	
	public ClienteAluno() {
		// TODO Auto-generated constructor stub
	}


	public ClienteAluno(Integer rm, String nome, String turma, String cpf, String rg, LocalDateTime dataNascimento,
			Set<ClienteAlunoEndereco> enderecos) {
		super();
		this.rm = rm;
		this.nome = nome;
		this.turma = turma;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.enderecos = enderecos;
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
	public Set<ClienteAlunoEndereco> getEnderecos() {
		return enderecos;
	}


	/**
	 * @param enderecos the enderecos to set
	 */
	public void setEnderecos(Set<ClienteAlunoEndereco> enderecos) {
		this.enderecos = enderecos;
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
