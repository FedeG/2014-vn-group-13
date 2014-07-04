package ar.edu.futbol5.distribucion;

import java.util.ArrayList;
import java.util.List;

import ar.edu.futbol5.Jugador;
import ar.edu.futbol5.Partido;

public class CriterioDistribucion {
	List<Integer> posicionesEquipo1 = new ArrayList<Integer>();
	List<Integer> posicionesEquipo2 = new ArrayList<Integer>();
	
	public List<Jugador> ObtenerEquipo1(Partido partido) {
		return ObtenerEquipo(partido, posicionesEquipo1);
	}
	public List<Jugador> ObtenerEquipo2(Partido partido) {
		return ObtenerEquipo(partido, posicionesEquipo2);
	}
	private List<Jugador> ObtenerEquipo(Partido partido, List<Integer> posiciones) {
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();	
		posiciones.forEach(p -> jugadores.add(partido.getInscriptos().get(p)));	
		return jugadores;
	}
}
