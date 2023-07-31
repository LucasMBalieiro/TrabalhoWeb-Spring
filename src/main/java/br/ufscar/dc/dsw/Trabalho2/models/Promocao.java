package br.ufscar.dc.dsw.Trabalho2.models;

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
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public SiteReserva getSiteReserva() {
		return siteReserva;
	}

	public void setSiteReserva(SiteReserva siteReserva) {
		this.siteReserva = siteReserva;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDataIni() {
		return dataIni;
	}

	public void setDataIni(String dataIni) {
		this.dataIni = dataIni;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

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
