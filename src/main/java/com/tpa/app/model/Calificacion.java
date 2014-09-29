package com.tpa.app.model;

public class Calificacion {

	private int nota;
	private Jugador jugador;
	private String critica;

	public Calificacion(int nota, Jugador jugador, String critica) {
		this.setCritica(critica);
		this.setNota(nota);
		this.setJugador(jugador);
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public String getCritica() {
		return critica;
	}

	public void setCritica(String critica) {
		this.critica = critica;
	}
}
