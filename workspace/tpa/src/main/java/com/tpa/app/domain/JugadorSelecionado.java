package com.tpa.app.domain;

import java.io.Serializable;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.Infraccion;
import com.tpa.app.Jugador;
import com.tpa.app.Persona;
import com.tpa.app.ui.PromedioTransformer;

@Observable
public class JugadorSelecionado implements Serializable {

	private Jugador jugador;

	public JugadorSelecionado(Jugador jugador) {
		this.jugador = jugador;
	}

	public Double getPromedio() {
		Double promedio = new PromedioTransformer().transform(this.getJugador());  
		if (promedio == null) promedio = 0.0;
		return promedio;
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