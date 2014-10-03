package com.tpa.app.model;

public abstract class Criterio {
	public abstract double dameTuValor(Inscripcion inscripcion);
	public abstract String getNombre();

	@Override
	public String toString() {
		return getNombre();
	}
}
