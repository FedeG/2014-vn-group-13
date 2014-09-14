package com.tpa.app;

public class PorHandicap implements Criterio {
	private String nombre = "Criterio Por Handicap";

	public String getNombre() {
		return nombre;
	}

	@Override
	public double dameTuValor(Inscripcion inscripcion) {
		return inscripcion.getJugador().getHandicap();
	}
}
