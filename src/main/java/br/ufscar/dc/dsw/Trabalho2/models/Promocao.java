package br.ufscar.dc.dsw.Trabalho2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Promocao")
public class Promocao extends AbstractEntity<Long> {
	
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	@JoinColumn(name="siteReserva_id")
	private SiteReserva siteReserva;
	
	
}
