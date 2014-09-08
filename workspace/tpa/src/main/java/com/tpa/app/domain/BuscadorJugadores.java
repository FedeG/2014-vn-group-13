package com.tpa.app.domain;

import java.io.Serializable;

import org.uqbar.commons.utils.Observable;


import com.tpa.app.Jugador;



@Observable
public class BuscadorJugadores implements Serializable {
	
	private Jugador jugadorSeleccionado;
	
	public void buscar() {
		//this.resultados = RepositorioCelulares.getInstance().search(this.numero, this.nombre);
	}

	public void verJugadorSeleccionado() {
		//this.resultados = RepositorioCelulares.getInstance().search(this.numero, this.nombre);
	}
	
	public Jugador getJugadorSeleccionado() {
		return this.jugadorSeleccionado;
	}
	
	public void setJugadorSeleccionado(Jugador celularSeleccionado) {
		this.jugadorSeleccionado = celularSeleccionado;
	}
}
