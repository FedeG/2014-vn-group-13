package com.tpa.app;

public class Calificacion {
	
	private int nota;
	private Jugador jugador;
	private String critica;
	
	public Calificacion(int nota, Jugador jugador, String critica) {
		this.critica = critica;
		this.nota = nota;
		this.jugador = jugador;
	}
}
