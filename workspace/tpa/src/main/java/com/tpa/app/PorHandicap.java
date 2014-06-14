package com.tpa.app;

import java.util.Collection;
import java.util.Optional;

import javafx.util.Pair;

@SuppressWarnings("restriction")
public class PorHandicap implements Criterio {
	private Collection<Pair<Jugador, Integer>> valoresDeJugadores;
	private String nombre = "Criterio Por Handicap";

	public PorHandicap(Collection<Pair<Jugador, Integer>> valoresDeJugadores) {
		this.setValoresDeJugadores(valoresDeJugadores);
	}

	public PorHandicap(String nombre,
			Collection<Pair<Jugador, Integer>> valoresDeJugadores) {
		this.setValoresDeJugadores(valoresDeJugadores);
		this.setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Pair<Jugador, Integer>> getValoresDeJugadores() {
		return valoresDeJugadores;
	}

	public void setValoresDeJugadores(
			Collection<Pair<Jugador, Integer>> valoresDeJugadores) {
		this.valoresDeJugadores = valoresDeJugadores;
	}

	@Override
	public double dameTuValor(Inscripcion inscripcion) {
		Optional<Pair<Jugador, Integer>> pair_buscado;
		pair_buscado = this.getValoresDeJugadores().stream()
				.filter(pair -> pair.getKey() == inscripcion.getJugador())
				.findFirst();
		return pair_buscado.get().getValue();
	}

}
