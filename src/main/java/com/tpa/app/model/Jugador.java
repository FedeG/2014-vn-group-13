package com.tpa.app.model;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;


@Observable
public class Jugador {

	private List<Infraccion> infracciones;
	private Persona persona;
	private List<Partido> partidosJugados;
	private Double handicap;

	public Jugador(Persona persona) {
		this.infracciones = new ArrayList<Infraccion>();
		this.partidosJugados = new ArrayList<Partido>();
		this.persona = persona;
		this.handicap = 0.0;
	}
	
	public Jugador(Persona persona, Double handicap) {
		this.infracciones = new ArrayList<Infraccion>();
		this.partidosJugados = new ArrayList<Partido>();
		this.persona = persona;
		this.handicap = handicap;
	}

	public List<Infraccion> getInfracciones() {
		return this.infracciones;
	}

	public void agregarInfraccion(Infraccion infraccion) {
		getInfracciones().add(infraccion);
	}

	public void proponer(Persona persona, Partido partido, Administrador admin, Inscripcion.PrioridadesInscripciones modalidad) {
		Propuesta propuesta = new Propuesta(persona, modalidad, partido);
		admin.agregarPropuesta(propuesta);
	}

	public void avisarAmigos(Partido partido) {
		this.getPersona().avisarAmigos(partido);
	}

	public Persona getPersona() {
		return persona;
	}

	public List<Partido> getPartidosJugados() {
		return partidosJugados;
	}

	public void agregarPartidoJugado(Partido partidoJugado) {
		this.getPartidosJugados().add(0, partidoJugado);
	}

	public Double getHandicap() {
		return handicap;
	}

	public void setHandicap(Double handicap) {
		this.handicap = handicap;
	}
	
	
}
