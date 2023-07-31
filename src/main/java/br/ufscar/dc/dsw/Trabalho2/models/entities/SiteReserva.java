package br.ufscar.dc.dsw.Trabalho2.models.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name="SiteReserva")
public class SiteReserva extends AbstractEntity<Long> {
	
	
	@NotBlank
	@Column(nullable = false, length = 64, unique = true)
	private String email;
	
	@NotBlank
	@Column(nullable = false, length = 64)
	private String senha;
	
	@NotBlank
	@Column(nullable = false, length = 64, unique = true)
	private String url;
	
	@NotBlank
	@Column(nullable = false, length = 32)
	private String nome;
	
	@NotBlank
	@Column(nullable = false, length = 20, unique = true)
	private String telefone;
	
	// Getters e Setters
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
