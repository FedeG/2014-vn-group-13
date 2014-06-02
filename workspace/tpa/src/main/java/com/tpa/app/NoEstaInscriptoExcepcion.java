package com.tpa.app;

public class NoEstaInscriptoExcepcion extends RuntimeException {

	public NoEstaInscriptoExcepcion(Jugador jugador) {
		super("Ya se encuentra inscripto al partido. El jugador: " + jugador.getPersona().getNombre());
	}

}