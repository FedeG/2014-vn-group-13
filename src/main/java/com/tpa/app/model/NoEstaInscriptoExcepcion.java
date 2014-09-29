package com.tpa.app.model;

public class NoEstaInscriptoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoEstaInscriptoExcepcion(Jugador jugador) {
		super("No se encuentra inscripto al partido. El jugador: "
				+ jugador.getPersona().getNombre());
	}

}
