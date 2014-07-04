package ar.edu.futbol5.distribucion;

import java.util.ArrayList;
import java.util.List;

import ar.edu.futbol5.Jugador;

public class CriterioDistribucion {
	public List<Integer> posicionesEquipo1 = new ArrayList<Integer>();
	public List<Integer> posicionesEquipo2 = new ArrayList<Integer>();

	public List<Jugador> ObtenerEquipo(List<Jugador> inscripciones, List<Integer> posiciones) {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();	
		posiciones.forEach(p -> jugadores.add(inscripciones.get(p)));	
		return jugadores;
	}
}
