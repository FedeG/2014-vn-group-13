package com.tpa.app;

import java.util.ArrayList;
import java.util.Collection;
import java.time.LocalDateTime;

public class Administrador {
	private ArrayList<Partido> partidos = new ArrayList<Partido>();

	public Collection<Partido> getPartidos() {
		return this.partidos;
	}

	public void crearPartido(LocalDateTime fecha_y_hora, String lugar, int cupo) {
		Partido partidoNuevo = new Partido(fecha_y_hora, lugar, cupo);
		this.partidos.add(partidoNuevo);
	}

}
