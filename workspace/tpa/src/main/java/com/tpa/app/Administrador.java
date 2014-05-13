package com.tpa.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

public class Administrador {
	private Collection<Partido> partidos;
	
	public Administrador() {
		this.partidos = new ArrayList<Partido>();
	}	
	public Collection<Partido> getPartidos() {
		return this.partidos;
	}
	public Partido crearPartido(LocalDateTime fecha_y_hora, String lugar, int cupo) {
		Partido partidoNuevo = new Partido(fecha_y_hora, lugar, cupo);
		this.partidos.add(partidoNuevo);
		return partidoNuevo;
	}
	public void generarEquiposTentativos(Partido partido)
	{
		partido.setEquipoA(partido.getInscripciones().stream().limit(5).collect(Collectors.toCollection(()-> new ArrayList<Inscripcion>())));
		partido.setEquipoB(partido.getInscripciones().stream().skip(5).collect(Collectors.toCollection(()-> new ArrayList<Inscripcion>())));
	}
}
