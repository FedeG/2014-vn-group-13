package com.tpa.app.domain;

import java.io.Serializable;
import java.util.List;

import org.uqbar.commons.utils.Observable;





import com.tpa.app.Jugador;
import com.tpa.app.Partido;
import com.tpa.app.repo.RepositorioJugadores;
import com.tpa.app.repo.RepositorioPartidos;

@Observable
public class BuscadorJugadores implements Serializable {
	
	private Jugador jugadorSeleccionado;
	private List<Jugador> resultados;
	private String nombre;

	
	public void search() {
		this.resultados = RepositorioJugadores.getInstance().search();
	}
	
	public Jugador getJugadorSeleccionado() {
		return this.jugadorSeleccionado;
	}
	
	public void setJugadorSeleccionado(Jugador celularSeleccionado) {
		this.jugadorSeleccionado = celularSeleccionado;
	}

	public List<Jugador> getResultados() {
		return resultados;
	}

	public void setResultados(List<Jugador> resultados) {
		this.resultados = resultados;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}
