package com.tpa.app.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.Partido;
import com.tpa.app.Jugador;

@Observable
public class SelectorJugadores implements Serializable {

	private Jugador jugadorSeleccionado;
	private List<Jugador> equipoA;
	private List<Jugador> equipoB;

	public SelectorJugadores(Partido partido) {
		List<Jugador> equipoA = (List<Jugador>)
			partido.getEquipoA()
				.stream()
				.map(inscripcion -> inscripcion.getJugador())
				.collect(Collectors.toList());
		List<Jugador> equipoB = (List<Jugador>) 
			partido.getEquipoB()
				.stream()
				.map(inscripcion -> inscripcion.getJugador())
				.collect(Collectors.toList());
		this.setEquipoA(equipoA);
		this.setEquipoB(equipoB);
	}

	public List<Jugador> getEquipoA() {
		return equipoA;
	}

	public void setEquipoA(List<Jugador> equipoA) {
		this.equipoA = equipoA;
	}

	public List<Jugador> getEquipoB() {
		return equipoB;
	}

	public void setEquipoB(List<Jugador> equipoB) {
		this.equipoB = equipoB;
	}

	public Jugador getJugadorSeleccionado() {
		return jugadorSeleccionado;
	}

	public void setJugadorSeleccionado(Jugador jugadorSeleccionado) {
		this.jugadorSeleccionado = jugadorSeleccionado;
	}

}
