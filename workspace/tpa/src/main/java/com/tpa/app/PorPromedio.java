package com.tpa.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PorPromedio implements Criterio {
	private int cantidadCalificaciones;
	private String nombre = "Criterio Por Promedio de n últimas calificaciones";
	private ArrayList<Partido> partidos;

	public PorPromedio(int n, ArrayList<Partido> partidos) {
		this.setCantidadCalificaciones(n);
		this.setPartidos(partidos);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int dameTuValor(Inscripcion inscripcion) {
		ArrayList<Partido> partidosCalificados = new ArrayList<Partido>();
		partidosCalificados = ((ArrayList<Partido>) this.partidos.stream()
				.filter(p -> p
						.getCalificaciones()
						.stream()
						.anyMatch(
								c -> c.getJugador().equals(
										inscripcion.getJugador()))));

		Collections.sort(partidosCalificados, new Comparator<Partido>() {
			public int compare(Partido p1, Partido p2) {
				return p1.getFechaHora().compareTo(p2.getFechaHora());
			}
		});
		int suma = 0;
		int i;
		int size = partidosCalificados.size();
		if (partidosCalificados.size() < this.getCantidadCalificaciones()) {
			for (Partido partido : partidosCalificados) {
				Calificacion calif = (Calificacion) partido
						.getCalificaciones()
						.stream()
						.filter(c -> c.getJugador().equals(
								inscripcion.getJugador()));
				suma += calif.getNota();
			}
			return suma / partidosCalificados.size();
		} else {
			for (i = 0; i < this.getCantidadCalificaciones() - 1; i++) {
				size = size - 1;
				Calificacion calif = (Calificacion) partidosCalificados
						.get(size)
						.getCalificaciones()
						.stream()
						.filter(c -> c.getJugador().equals(
								inscripcion.getJugador()));
				suma += calif.getNota();
			}
			return suma / this.getCantidadCalificaciones();
		}

	}

	public int getCantidadCalificaciones() {
		return cantidadCalificaciones;
	}

	public void setCantidadCalificaciones(int cantidadCalificaciones) {
		this.cantidadCalificaciones = cantidadCalificaciones;
	}

	public ArrayList<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(ArrayList<Partido> partidos) {
		this.partidos = partidos;
	}

}
