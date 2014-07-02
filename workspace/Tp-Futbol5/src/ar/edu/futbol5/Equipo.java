package ar.edu.futbol5;

import java.util.ArrayList;
import java.util.List;

import ar.edu.futbol5.utilitarios.Lists;

class Equipo {
	
	private List<Jugador> jugadores;

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public void distribuir (List<Jugador> jugadoresOrdenados, List<Integer> posiciones) {
		jugadores = new ArrayList<Jugador>();
		posiciones.forEach(p -> jugadores.add(jugadoresOrdenados.get(p)));	

	}
	
	
}