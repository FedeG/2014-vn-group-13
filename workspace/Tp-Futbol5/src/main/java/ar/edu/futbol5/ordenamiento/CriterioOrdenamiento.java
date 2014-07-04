package ar.edu.futbol5.ordenamiento;

import ar.edu.futbol5.Jugador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CriterioOrdenamiento {
	
	public List<Jugador> ordenar(List<Jugador> inscripciones) {
		Collections.sort(inscripciones, 
				(jugador1, jugador2) -> calcularValor(jugador1).compareTo(calcularValor(jugador2)));

		Collections.reverse(inscripciones);
		
		List<Jugador> jugadores=new ArrayList<Jugador>();
		jugadores.addAll(inscripciones);
		return jugadores;
	}

	public Double calcularValor(Jugador jugador) {
		return null;
	}
		 
}
