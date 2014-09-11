package com.tpa.app.repo;

import java.util.Date;

public class JugadorSearchParameter {
	private String comienzaCon;
	private String contiene;
	private Double handicapDesde;
	private Double handicapHasta;
	private Double promedioDesde;
	private Double promedioHasta;
	private String tuvoInfraccion;
	private Date antesDe;

	public JugadorSearchParameter(String comienzaCon, String contiene,
			Double handicapDesde, Double handicapHasta,
			Double promedioDesde, Double promedioHasta,
			String tuvoInfraccion, Date antesDe) {
		this.comienzaCon = comienzaCon;
		this.contiene = contiene;
		this.handicapDesde = handicapDesde;
		this.handicapHasta = handicapHasta;
		this.promedioDesde = promedioDesde;
		this.promedioHasta = promedioHasta;
		this.tuvoInfraccion = tuvoInfraccion;
		this.antesDe = antesDe;
	}

	public String getComienzaCon() {
		return comienzaCon;
	}

	public void setComienzaCon(String comienzaCon) {
		this.comienzaCon = comienzaCon;
	}

	public String getContiene() {
		return contiene;
	}

	public void setContiene(String contiene) {
		this.contiene = contiene;
	}

	public Double getHandicapDesde() {
		return handicapDesde;
	}

	public void setHandicapDesde(Double handicapDesde) {
		this.handicapDesde = handicapDesde;
	}

	public Double getHandicapHasta() {
		return handicapHasta;
	}

	public void setHandicapHasta(Double handicapHasta) {
		this.handicapHasta = handicapHasta;
	}

	public Double getPromedioDesde() {
		return promedioDesde;
	}

	public void setPromedioDesde(Double promedioDesde) {
		this.promedioDesde = promedioDesde;
	}

	public Double getPromedioHasta() {
		return promedioHasta;
	}

	public void setProdemioHasta(Double prodemioHasta) {
		this.promedioHasta = prodemioHasta;
	}

	public String getTuvoInfraccion() {
		return tuvoInfraccion;
	}

	public void setTuvoInfraccion(String tuvoInfraccion) {
		this.tuvoInfraccion = tuvoInfraccion;
	}

	public Date getAntesDe() {
		return antesDe;
	}

	public void setAntesDe(Date antesDe) {
		this.antesDe = antesDe;
	}
}