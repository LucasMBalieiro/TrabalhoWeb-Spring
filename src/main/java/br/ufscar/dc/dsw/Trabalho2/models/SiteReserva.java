package br.ufscar.dc.dsw.Trabalho2.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name="SiteReserva")
public class SiteReserva extends Usuario {
	
	
	@NotBlank(message = "{NotNull.site.url}")
	@Column(nullable = false, length = 64, unique = true)
	private String url;
	
	@NotBlank(message = "{NotNull.site.nome}")
	@Column(nullable = false, length = 32)
	private String nome;
	
	@NotBlank(message = "{NotNull.site.telefone}")
	@Column(nullable = false, length = 20)
	private String telefone;
	
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
