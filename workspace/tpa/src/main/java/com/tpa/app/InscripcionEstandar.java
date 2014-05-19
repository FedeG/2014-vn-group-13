package com.tpa.app;

public class InscripcionEstandar extends Inscripcion{
	
	public InscripcionEstandar(Jugador jugador)
	{
		this.activo = true;
		this.jugador = jugador;
	}
		
	@Override
	public PrioridadesInscripciones dameTuPrioridad()
	{
		return PrioridadesInscripciones.Estandar;
	}
}
