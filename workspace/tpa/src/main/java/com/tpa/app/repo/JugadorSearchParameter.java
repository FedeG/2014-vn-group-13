package com.tpa.app.repo;

import java.util.Date;

import org.uqbar.commons.utils.Observable;

@Observable
public class JugadorSearchParameter {
	private String comienzaCon = "";
	private String contiene = "";
	private Double handicapDesde = null;
	private Double handicapHasta = null;
	private Double promedioDesde = null;
	private Double promedioHasta = null;
	private String tuvoInfraccion = "Todos";
	private Date antesDe = null;

	public void borrarParametros()
	{
		this.comienzaCon = this.contiene = "";
		this.tuvoInfraccion = "Todos";
		this.handicapDesde = this.handicapHasta = this.promedioDesde = this.promedioHasta = null;
		this.antesDe = null;
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