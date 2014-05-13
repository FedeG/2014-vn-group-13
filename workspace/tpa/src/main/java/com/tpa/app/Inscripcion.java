package com.tpa.app;

public abstract class Inscripcion {
	public enum PrioridadesInscripciones{
		Estandar,
		Solidaria,
		Condicional
	}
	
	protected Jugador jugador;
	protected boolean activo;
	
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public abstract PrioridadesInscripciones dameTuPrioridad();
}
