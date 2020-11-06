package br.com.fiapspring.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class ClienteAluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rm")
	private Integer rm;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "rg")
	private String rg;
	@Column(name = "dataNacimento")
	private LocalDateTime dataNascimento;
	
	public ClienteAluno() {
		// TODO Auto-generated constructor stub
	}

	public ClienteAluno(Integer rm, String nome, String cpf, String rg, LocalDateTime dataNascimento) {
		super();
		this.rm = rm;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
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
	
	
	
	
	
}
