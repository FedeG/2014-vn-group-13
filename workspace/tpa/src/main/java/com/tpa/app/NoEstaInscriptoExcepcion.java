package com.tpa.app;

public class NoEstaInscriptoExcepcion extends RuntimeException {

	public NoEstaInscriptoExcepcion(Jugador jugador) {
		super("No se encuentra inscripto al partido. El jugador: " + jugador.getPersona().getNombre());
	}

}
