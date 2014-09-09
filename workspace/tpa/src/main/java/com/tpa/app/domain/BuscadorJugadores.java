package com.tpa.app.domain;

import java.io.Serializable;
import java.util.List;

import org.uqbar.commons.utils.Observable;




import com.tpa.app.Jugador;
import com.tpa.app.Partido;
import com.tpa.app.repo.RepositorioPartidos;



@Observable
public class BuscadorJugadores implements Serializable {
	
	private Jugador jugadorSeleccionado;
	private List<Jugador> resultados;
	
	public void search() {
		//this.setResultados(RepositorioPartidos.getInstance().searchJugadoresEquipoA());
	}
	
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

	public List<Jugador> getResultados() {
		return resultados;
	}

	public void setResultados(List<Jugador> resultados) {
		this.resultados = resultados;
	}
}
