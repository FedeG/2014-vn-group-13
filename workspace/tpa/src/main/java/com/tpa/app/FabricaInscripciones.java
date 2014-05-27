package com.tpa.app;

import com.tpa.app.Inscripcion.PrioridadesInscripciones;

public class FabricaInscripciones {

	public static Inscripcion crearInscripcion(
			PrioridadesInscripciones modalidad, Jugador jugador) {
		Inscripcion insc = null;
		switch (modalidad) {
		case Estandar:
			insc = new InscripcionEstandar(jugador);
			break;
		case Condicional:
			insc = new InscripcionCondicional(jugador, null);
			break;
		case Solidaria:
			insc = new InscripcionSolidaria(jugador);
			break;
		}
		return insc;
	}
}
