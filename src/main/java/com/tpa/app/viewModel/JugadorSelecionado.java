package com.tpa.app.viewModel;

import java.io.Serializable;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.model.Infraccion;
import com.tpa.app.model.Jugador;
import com.tpa.app.model.Persona;
import com.tpa.app.view.PromedioTransformer;

@Observable
public class JugadorSelecionado implements Serializable {

	private Jugador jugador;

	public JugadorSelecionado(Jugador jugador) {
		this.jugador = jugador;
	}

	public Double getPromedio() {
		return new PromedioTransformer().transform(this.getJugador());  
	}

	public List<Infraccion> getInfracciones() {
		return jugador.getInfracciones();
	}

	public List<Persona> getAmigos() {
		return jugador.getPersona().getAmigos();
	}

	public Jugador getJugador() {
		return jugador;
	}

}