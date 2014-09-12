package com.tpa.app.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.Jugador;
import com.tpa.app.repo.JugadorSearchParameter;
import com.tpa.app.repo.RepositorioJugadores;

@Observable
public class BuscadorJugadores implements Serializable {

	private Jugador jugadorSeleccionado;
	private List<Jugador> resultados;
	private JugadorSearchParameter jugadorSearchParameter = new JugadorSearchParameter();
	//private String comienzaCon = "", contiene = "", tuvoInfraccion = "Todos";
	//private Double handicapDesde = null, handicapHasta = null,
	//		promedioDesde = null, promedioHasta = null;
	//private Date antesDe;

	public void search() {
		this.resultados = RepositorioJugadores.getInstance().search(jugadorSearchParameter);
				/*new JugadorSearchParameter(this.comienzaCon, this.contiene,
						this.handicapDesde, this.handicapHasta,
						this.promedioDesde, this.promedioHasta,
						this.tuvoInfraccion, this.antesDe));*/
	}

	public void borrarBusqueda() {
		jugadorSearchParameter.borrarParametros();
	}

	public Jugador getJugadorSeleccionado() {
		return this.jugadorSeleccionado;
	}

	public void setJugadorSeleccionado(Jugador jugador) {
		this.jugadorSeleccionado = jugador;
	}

	public List<Jugador> getResultados() {
		return resultados;
	}

	public void setResultados(List<Jugador> resultados) {
		this.resultados = resultados;
	}
	public JugadorSearchParameter getJugadorSearchParameter()
	{
		return jugadorSearchParameter;
	}
	public void setResultados(JugadorSearchParameter jugadorSearchParameter) {
		this.jugadorSearchParameter = jugadorSearchParameter;
	}
/*
	public String getComienzaCon() {
		return this.comienzaCon;
	}

	public void setComienzaCon(String iniciales) {
		this.comienzaCon = iniciales;
	}

	public String getContiene() {
		return this.contiene;
	}

	public void setContiene(String letras) {
		this.contiene = letras;
	}

	public Double getHandicapDesde() {
		return this.handicapDesde;
	}

	public void setHandicapDesde(Double handicap) {
		this.handicapDesde = handicap;
	}

	public Double getHandicapHasta() {
		return this.handicapHasta;
	}

	public void setHandicapHasta(Double handicap) {
		this.handicapHasta = handicap;
	}

	public Double getPromedioDesde() {
		return this.promedioDesde;
	}

	public void setPromedioDesde(Double promedio) {
		this.promedioDesde = promedio;
	}

	public Double getPromedioHasta() {
		return this.promedioHasta;
	}

	public void setPromedioHasta(Double promedio) {
		this.promedioHasta = promedio;
	}

	public String getTuvoInfraccion() {
		return this.tuvoInfraccion;
	}

	public void setTuvoInfraccion(String opcion) {
		this.tuvoInfraccion = opcion;
	}

	public Date getAntesDe() {
		return this.antesDe;
	}

	public void setAntesDe(Date unaFecha) {
		this.antesDe = unaFecha;
	}*/
}
