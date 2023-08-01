package br.ufscar.dc.dsw.Trabalho2.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false, columnDefinition = "Date", name = "dateInicio")
	private LocalDate dataInicio;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false, columnDefinition = "Date", name = "dateFim")
	private LocalDate dataFim;

	public Promocao(@NotNull Hotel hotel, @NotNull SiteReserva siteReserva, @NotNull BigDecimal valor, @NotNull LocalDate dataInicio, @NotNull LocalDate dataFim) {
		this.hotel = hotel;
		this.siteReserva = siteReserva;
		this.valor = valor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public Promocao() {

	}

	public Promocao(SiteReserva s1, Hotel h1, int i, Date date, Date date1) {
		super();
	}

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

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
}
