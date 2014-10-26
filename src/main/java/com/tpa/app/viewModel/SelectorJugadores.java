package com.tpa.app.viewModel;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.model.Inscripcion;
import com.tpa.app.model.Jugador;
import com.tpa.app.model.Partido;

@SuppressWarnings("serial")
@Observable
public class SelectorJugadores implements Serializable {

	private Jugador jugadorSeleccionado;
	private Partido partido;

	public SelectorJugadores(Partido partido) {
		this.partido = partido;
	}

	public List<Jugador> getEquipoA() {
		return get_jugadores(this.getPartido().getEquipoA());
	}

	public List<Jugador> getEquipoB() {
		return get_jugadores(this.getPartido().getEquipoB());
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

	public Partido getPartido() {
		return partido;
	}

}
