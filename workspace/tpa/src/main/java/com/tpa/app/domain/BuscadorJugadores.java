package com.tpa.app.domain;

import java.io.Serializable;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.Jugador;
import com.tpa.app.repo.JugadorSearchParameter;
import com.tpa.app.repo.RepositorioJugadores;

@Observable
public class BuscadorJugadores implements Serializable {

	private Jugador jugadorSeleccionado;
	private List<Jugador> resultados;
	private JugadorSearchParameter jugadorSearchParameter = new JugadorSearchParameter();

	public void search() {
		this.resultados = RepositorioJugadores.getInstance().search(jugadorSearchParameter);
	}

	public void borrarBusqueda() {
		jugadorSearchParameter.borrarParametros();
	}

	public Jugador getJugadorSeleccionado() {
		return this.jugadorSeleccionado;
	}

	public void setJugadorSeleccionado(Jugador jugador) {
		this.jugadorSeleccionado = jugador;
	}

	public List<Jugador> getResultados() {
		return resultados;
	}

	public void setResultados(List<Jugador> resultados) {
		this.resultados = resultados;
	}
	public JugadorSearchParameter getJugadorSearchParameter()
	{
		return jugadorSearchParameter;
	}
	public void setResultados(JugadorSearchParameter jugadorSearchParameter) {
		this.jugadorSearchParameter = jugadorSearchParameter;
	}

}
