package com.tpa.app;

import java.time.LocalDateTime;
import java.util.Collection; //Buscar de SortedSet o SortedMap

public class Partido {

	private String lugar;
	private LocalDateTime fechaHora;
	private int cupo;
	private Collection<Jugador> jugadoresEstandar;
	private Collection<Jugador> jugadoresSolidarios;
	private Collection<Jugador> jugadoresCondicionales;

	public Partido(LocalDateTime fecha_y_hora, String lugar, int cupo) {
		this.setFechaHora(fecha_y_hora);
		this.setLugar(lugar);
		this.setCupo(cupo);
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Collection<Jugador> getJugadoresEstandar() {
		return jugadoresEstandar;
	}

	public Collection<Jugador> getJugadoresSolidarios() {
		return jugadoresSolidarios;
	}

	public Collection<Jugador> getJugadoresCondicionales() {
		return jugadoresCondicionales;
	}

	public void inscribirCondicional(Jugador jugador) {
		this.jugadoresCondicionales.add(jugador);
	}

	public void inscribirEstadar(Jugador jugador) {
		this.jugadoresEstandar.add(jugador);
	}

	public void inscribirSolidario(Jugador jugador) {
		this.jugadoresSolidarios.add(jugador);
	}

}