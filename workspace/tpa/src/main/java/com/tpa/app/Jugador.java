package com.tpa.app;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

	private List<Infraccion> infracciones;
	private Persona persona;

	public Jugador(Persona persona) {
		this.infracciones = new ArrayList<Infraccion>();
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
		this.getPersona().avisarAmigos(partido);
	}

	public int calificar(Jugador jugador, Partido partido, int nota,
			String critica) {

		if (validarCombinacionPartidoJugadores(jugador, partido, this)) {
			Calificacion calificacion = new Calificacion(nota, jugador, critica);
			partido.agregarCalificacion(calificacion);
			return 0;
		} else {
			return 1;
		}

	}

	public boolean validarCombinacionPartidoJugadores(
			Jugador jugadorACalificar, Partido partido,
			Jugador jugadorCalificador) {
		return (partido.getInscripciones().stream().anyMatch(i -> i.jugador
				.equals(jugadorCalificador)))
				&& (partido.getInscripciones().stream().anyMatch(i -> i.jugador
						.equals(jugadorACalificar)));

	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
