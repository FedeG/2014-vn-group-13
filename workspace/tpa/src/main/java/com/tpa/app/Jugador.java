package com.tpa.app;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

	private List<Infraccion> infracciones;
	private Persona persona;
	private List<Partido> partidosJugados;

	public Jugador(Persona persona) {
		this.infracciones = new ArrayList<Infraccion>();
		this.setPartidosJugados(new ArrayList<Partido>());
		this.setPersona(persona);
	}

	public List<Infraccion> getInfracciones() {
		return this.infracciones;
	}

	public void agregarInfraccion(Infraccion infraccion) {
		getInfracciones().add(infraccion);
	}

	public void proponer(Persona persona, Partido partido, Administrador admin,
			Inscripcion.PrioridadesInscripciones modalidad) {
		Propuesta propuesta = new Propuesta(persona, modalidad, partido);
		admin.agregarPropuesta(propuesta);
	}

	public void avisarAmigos(Partido partido) {
		// this.getPersona().avisarAmigos(partido);
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Partido> getPartidosJugados() {
		return partidosJugados;
	}

	public void agregarPartidoJugado(Partido partidoJugado) {
		this.getPartidosJugados().add(partidoJugado);
	}

	public void setPartidosJugados(List<Partido> partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

}
