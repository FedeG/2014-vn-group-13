package com.tpa.app.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.Jugador;
import com.tpa.app.repo.*;

@Observable
public class BuscadorJugadores implements Serializable {

	private Jugador jugadorSeleccionado;
	private List<Jugador> resultados;
	private BusquedaMultiple filtros;
	
	private Double promedioDesde, promedioHasta, handicapDesde, handicapHasta;
	private String comienzaCon, apodoContiene, opcionInfraccion;
	private Date fechaTope;
	
	public void search() {
		filtros = new BusquedaMultiple();
		filtros.agregarCriterio(new BusquedaPorComienzoDeNombre(comienzaCon));
		filtros.agregarCriterio(new BusquedaPorMatchearApodo(apodoContiene));
		filtros.agregarCriterio(new BusquedaPorPromedio(promedioDesde,promedioHasta));
		filtros.agregarCriterio(new BusquedaPorHandicap(handicapDesde, handicapHasta));
		filtros.agregarCriterio(new BusquedaPorInfraccion(opcionInfraccion));
		filtros.agregarCriterio(new BusquedaPorFechaNacimiento(fechaTope));
		this.resultados = RepositorioJugadores.getInstance().search(filtros);
	}
	public void borrarBusqueda(){
		promedioDesde = promedioHasta = handicapDesde = handicapHasta = null;
		comienzaCon = apodoContiene = null;
		opcionInfraccion = "Indistinto";
		fechaTope = null;
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
	public void setPromedioHasta(Double promedioHasta) {
		this.promedioHasta = promedioHasta;
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
	public String getComienzaCon() {
		return comienzaCon;
	}
	public void setComienzaCon(String comienzaCon) {
		this.comienzaCon = comienzaCon;
	}
	public String getApodoContiene() {
		return apodoContiene;
	}
	public void setApodoContiene(String apodoContiene) {
		this.apodoContiene = apodoContiene;
	}
	public String getOpcionInfraccion() {
		return opcionInfraccion;
	}
	public void setOpcionInfraccion(String opcionInfraccion) {
		this.opcionInfraccion = opcionInfraccion;
	}
	public Date getFechaTope() {
		return fechaTope;
	}
	public void setFechaTope(Date fechaTope) {
		this.fechaTope = fechaTope;
	}
}
