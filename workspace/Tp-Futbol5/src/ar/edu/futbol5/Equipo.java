package ar.edu.futbol5;

import java.util.List;

// Code Smell: "Data Class"
// Lo único que tiene la clase es un atributo, su getter y su setter.

class Equipo {
	
	private List<Jugador> jugadores;

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	
}

