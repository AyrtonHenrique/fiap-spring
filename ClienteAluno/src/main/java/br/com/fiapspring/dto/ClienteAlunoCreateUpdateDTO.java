/**
 * 
 */
package br.com.fiapspring.dto;

import java.time.LocalDate;


/**
 * @author SaraRegina
 *
 */
public class ClienteAlunoCreateUpdateDTO {

	private Integer rm;
	private String nome;
	private String turma;
	private String cpf;
	private String rg;
	private LocalDate dataNascimento;
	private Boolean isCliente;
	
	
	
	
	
	/**
	 * 
	 */
	public ClienteAlunoCreateUpdateDTO() {
		
	}
	
	
	

	/**
	 * @param rm
	 * @param nome
	 * @param turma
	 * @param cpf
	 * @param rg
	 * @param dataNascimento
	 * @param isCliente
	 */
	public ClienteAlunoCreateUpdateDTO(Integer rm, String nome, String turma, String cpf, String rg,
			LocalDate dataNascimento, Boolean isCliente) {
		super();
		this.rm = rm;
		this.nome = nome;
		this.turma = turma;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.isCliente = isCliente;
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

	
	

}
