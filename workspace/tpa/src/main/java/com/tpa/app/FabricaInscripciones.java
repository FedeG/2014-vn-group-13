package com.tpa.app;

import com.tpa.app.Inscripcion.PrioridadesInscripciones;

public class FabricaInscripciones {

	public static Inscripcion crearInscripcion(
			PrioridadesInscripciones modalidad, Jugador jugador) {
		Inscripcion insc = null;
		switch (modalidad) {
		case ESTANDAR:
			insc = new Inscripcion(jugador, PrioridadesInscripciones.ESTANDAR, null);
			break;
		case CONDICIONAL:
			insc = new Inscripcion(jugador,PrioridadesInscripciones.CONDICIONAL, null);
			break;
		case SOLIDARIA:
			insc = new Inscripcion(jugador, PrioridadesInscripciones.SOLIDARIA, null);
			break;
		}
		return insc;
	}
}
