package com.tpa.app;

public class InscripcionSolidaria extends Inscripcion{
	
	public InscripcionSolidaria(Jugador jugador)
	{
		this.activo = true;
		this.jugador = jugador;
	}
	
	@Override
	public PrioridadesInscripciones dameTuPrioridad()
	{
		return PrioridadesInscripciones.Solidaria;
	}
}
