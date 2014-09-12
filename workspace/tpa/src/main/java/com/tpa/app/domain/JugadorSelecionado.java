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

	private Double promedio;
	private List<Infraccion> infracciones;
	private List<Persona> amigos;
	private Jugador jugador;

	public JugadorSelecionado(Jugador jugador) {
		this.jugador = jugador;
		this.infracciones = jugador.getInfracciones();
		this.amigos = jugador.getPersona().getAmigos();
		this.promedio = new PromedioTransformer().transform(this.getJugador());  
		if (this.promedio == null) this.promedio = 0.0;
	}

	public Double getPromedio() {
		return promedio;
	}

	public List<Infraccion> getInfracciones() {
		return infracciones;
	}

	public List<Persona> getAmigos() {
		return amigos;
	}

	public Jugador getJugador() {
		return jugador;
	}

}