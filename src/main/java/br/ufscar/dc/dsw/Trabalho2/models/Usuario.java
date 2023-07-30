package br.ufscar.dc.dsw.Trabalho2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import validation.UniqueEmail;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity<Long> {

	
	@NotNull(message = "{NotNull.usuario.email}")
	@UniqueEmail(message = "{NotUnique.usuario.email}")
	@Column(nullable = false, length = 19, unique = true)
	private String email;
	
	@NotNull(message = "{NotNull.usuario.senha}")
	@Column(nullable = false, length = 64)
	private String senha;
	
	@NotNull(message = "{NotNull.usuario.papel}")
	@Column(nullable = false, length = 3)
	private String tipo;

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
