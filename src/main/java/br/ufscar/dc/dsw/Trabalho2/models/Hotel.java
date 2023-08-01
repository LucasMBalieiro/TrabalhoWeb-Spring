package br.ufscar.dc.dsw.Trabalho2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name="hotel")
public class Hotel extends Usuario{


	@NotBlank
	@Column(nullable = false, length = 64, unique = true)
	private String cnpj;
	
	@NotBlank
	@Column(nullable = false, length = 32)
	private String nome;
	
	@NotBlank
	@Column(nullable = false, length = 32)
	private String cidade;

	public Hotel() {

	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

<<<<<<< Updated upstream
	public Hotel(String cnpj, String nome, String cidade) {
=======

	public String getEmail() {
		return super.getEmail();
	}




	public Hotel(String email, String senha, String tipo, String cnpj, String nome, String cidade) {
		super(email, senha, tipo);
>>>>>>> Stashed changes
		this.cnpj = cnpj;
		this.nome = nome;
		this.cidade = cidade;
	}

}
