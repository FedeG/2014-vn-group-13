package com.tpa.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PorCalificacion implements Criterio {
	private String nombre = "Por Calificacion";
	private int cantidadDeCalificaciones;

	public PorCalificacion(int cantidadDeCalificaciones) {
		this.setCantidadDeCalificaciones(cantidadDeCalificaciones);
	}

	public PorCalificacion(String nombre, int cantidadDeCalificaciones) {
		this(cantidadDeCalificaciones);
		this.setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidadDeCalificaciones() {
		return cantidadDeCalificaciones;
	}

	public void setCantidadDeCalificaciones(int cantidadDeCalificaciones) {
		this.cantidadDeCalificaciones = cantidadDeCalificaciones;
	}

	@Override
	public double dameTuValor(Inscripcion inscripcion) {
		List<Calificacion> calificacionesJugadorEnPartido = this
				.getUltimasNCalificacionesDelJugador(inscripcion.getJugador(),
						this.getCantidadDeCalificaciones());
		double valor = calificacionesJugadorEnPartido.stream()
				.mapToInt(calificacion -> calificacion.getNota()).average()
				.getAsDouble();
		return valor;
	}

	private List<Calificacion> getUltimasNCalificacionesDelJugador(
			Jugador jugador, int cantidadDeCalificacionesBuscadas) {
		ArrayList<Calificacion> calificacionesJugadorEnPartido = new ArrayList<Calificacion>();
		int calificacionesAgregadas = 0;
		int cantidadPartidos = jugador.getPartidosJugados().size();
		for (int numeroDePartido = 0; numeroDePartido < cantidadPartidos
				&& calificacionesAgregadas < cantidadDeCalificacionesBuscadas; numeroDePartido++) {
			ArrayList<Calificacion> calificaciones = this
					.getCalificacionDelJugadorEnElPartido(jugador,
							numeroDePartido);
			calificacionesJugadorEnPartido.addAll(calificaciones);
			calificacionesAgregadas += calificaciones.size();
		}
		return calificacionesJugadorEnPartido.subList(0,
				cantidadDeCalificacionesBuscadas);
	}

	private ArrayList<Calificacion> getCalificacionDelJugadorEnElPartido(
			Jugador jugador, int numeroDePartido) {
		return (ArrayList<Calificacion>) jugador
				.getPartidosJugados()
				.get(numeroDePartido)
				.getCalificaciones()
				.stream()
				.filter(calificacion -> calificacion.getJugador().equals(
						jugador)).collect(Collectors.toList());

	}
}