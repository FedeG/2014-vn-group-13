package com.tpa.app;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

	private List<Infraccion> infracciones;
	private Persona persona;

	public Jugador() {
		this.infracciones = new ArrayList<Infraccion>();
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
		this.persona.avisarAmigos(partido);
	}
	
	public void calificar(Jugador jugador, Partido partido, int nota, String critica) {
		Calificacion calificacion = new Calificacion(nota, jugador, critica);
		partido.agregarCalificacion(calificacion);
		
	}

}
