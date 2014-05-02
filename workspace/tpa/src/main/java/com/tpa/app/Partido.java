package com.tpa.app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class Partido {

	private String lugar;
	private LocalDateTime fechaHora;
	private int cupo;
	private SortedMap<Integer, Collection<Jugador>> jugadoresFijos;
	private SortedMap<Integer, Collection<Jugador>> jugadoresCondicionales;

	public Partido(LocalDateTime fecha_y_hora, String lugar, int cupo) {
		this.setFechaHora(fecha_y_hora);
		this.setLugar(lugar);
		this.setCupo(cupo);
		this.jugadoresCondicionales = new TreeMap<Integer, Collection<Jugador>>();
		this.jugadoresCondicionales.put(0, new ArrayList<Jugador>()); // Jugadores condicionales
		this.jugadoresFijos = new TreeMap<Integer, Collection<Jugador>>();
		this.jugadoresFijos.put(0, new ArrayList<Jugador>()); // Jugadores estandar
		this.jugadoresFijos.put(1, new ArrayList<Jugador>()); // Jugadores solidarios
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
	
	public SortedMap<Integer, Collection<Jugador>> getJugadoresFijos() {
		return jugadoresFijos;
	}

	public SortedMap<Integer, Collection<Jugador>> getJugadoresCondicionales() {
		return jugadoresCondicionales;
	}
	
	public void inscribirCondicional(Jugador jugador) {
		this.getJugadoresCondicionales().get(0).add(jugador);
	}

	public void inscribirEstadar(Jugador jugador) {
		this.getJugadoresFijos().get(0).add(jugador);
	}

	public void inscribirSolidario(Jugador jugador) {
		this.getJugadoresFijos().get(1).add(jugador);
	}

	public Collection<Jugador> getJugadoresSolidarios() {
		return this.getJugadoresFijos().get(1);
	}
	
	public Collection<Jugador> getJugadoresEstandar() {
		return this.getJugadoresFijos().get(0);
	}
	
	public Collection<Jugador> getJugadoresConCondicion() {
		return this.getJugadoresCondicionales().get(0);
	}

}