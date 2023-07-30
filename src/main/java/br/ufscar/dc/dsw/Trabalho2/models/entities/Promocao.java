package br.ufscar.dc.dsw.Trabalho2.models.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "Promocao")
public class Promocao extends AbstractEntity<Long> {
	
	@NotNull
	@OneToOne
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	@NotNull
	@OneToOne
	@JoinColumn(name="siteReserva_id")
	private SiteReserva siteReserva;
	
	@NotNull
	@Column(columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private BigDecimal valor;
	
	@NotNull
	@Column(nullable = false, length = 19)
	private String dataIni;
	
	@NotNull
	@Column(nullable = false, length = 19)
	private String dataFim;
}
