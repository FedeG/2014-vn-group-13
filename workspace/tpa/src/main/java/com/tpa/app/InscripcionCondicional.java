package com.tpa.app;

import java.util.function.Predicate;

public class InscripcionCondicional extends Inscripcion{
	private Predicate<Partido> condicion;

	public InscripcionCondicional(Jugador jugador, Predicate<Partido> condicion)
	{
		this.setCondicion(condicion);
		this.setActivo(true);
		this.setJugador(jugador);
	}
	public Predicate<Partido> getCondicion() {
		return this.condicion;
	}

	public void setCondicion(Predicate<Partido> condicion) {
		this.condicion = condicion;
	}
	@Override
	public PrioridadesInscripciones dameTuPrioridad()
	{
		return PrioridadesInscripciones.Solidaria;
	}
}
