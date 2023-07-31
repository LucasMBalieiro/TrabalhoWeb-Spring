package br.ufscar.dc.dsw.Trabalho2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
		super("HOT");
	}

	public Hotel(@NotNull String email, @NotNull String senha, @NotNull String cnpj, String nome, String cidade) {
		super(email, senha, "HOT");
		this.cnpj = cnpj;
		this.nome = nome;
		this.cidade = cidade;
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
}
