package com.tpa.app.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.Inscripcion;
import com.tpa.app.Partido;
import com.tpa.app.Jugador;

@Observable
public class SelectorJugadores implements Serializable {

	private Jugador jugadorSeleccionado;
	private Partido partido;

	public SelectorJugadores(Partido partido) {
		this.partido = partido;
	}

	public List<Jugador> getEquipoA() {
		return get_jugadores(this.partido.getEquipoA());
	}

	public List<Jugador> getEquipoB() {
		return get_jugadores(this.partido.getEquipoB());
	}
	
	private List<Jugador> get_jugadores(List<Inscripcion> inscripciones){
		return inscripciones.stream()
		.map(inscripcion -> inscripcion.getJugador())
		.collect(Collectors.toList());
	}

	public Jugador getJugadorSeleccionado() {
		return jugadorSeleccionado;
	}

	public void setJugadorSeleccionado(Jugador jugadorSeleccionado) {
		this.jugadorSeleccionado = jugadorSeleccionado;
	}

}
