package com.tpa.app;

import java.util.ArrayList;

public class PorCalificacion implements Criterio {
	private String nombre = "Criterio Por Calificacion";
	private int cantidadDeCalificaciones;

	public PorCalificacion(int cantidadDeCalificaciones) {
		this.setCantidadDeCalificaciones(cantidadDeCalificaciones);
	}

	public PorCalificacion(String nombre, int cantidadDeCalificaciones) {
		this.setNombre(nombre);
		this.setCantidadDeCalificaciones(cantidadDeCalificaciones);
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

	@SuppressWarnings("unchecked")
	@Override
	public int dameTuValor(Inscripcion inscripcion) {

		ArrayList<Calificacion> calificacionesPartido = new ArrayList<Calificacion>();
		ArrayList<Calificacion> calificacionesJugadorEnPartido = new ArrayList<Calificacion>();
		calificacionesPartido = (ArrayList<Calificacion>) inscripcion
				.getJugador().getPartidosJugados()
				.get(inscripcion.getJugador().getPartidosJugados().size() - 1)
				.getCalificaciones();
		calificacionesJugadorEnPartido = (ArrayList<Calificacion>) calificacionesPartido
				.stream().filter(
						c -> c.getJugador().equals(inscripcion.getJugador()));
		int total = 0;
		for (Calificacion c : calificacionesJugadorEnPartido) {
			total += c.getNota();
		}
		return total / calificacionesJugadorEnPartido.size();
	}

}
