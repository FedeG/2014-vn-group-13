package ar.edu.futbol5.ordenamiento;

import ar.edu.futbol5.Jugador;

public class OrdenamientoPorHandicap extends CriterioOrdenamiento {
	
	public Double calcularValor(Jugador jugador) {
		return jugador.getCalificacion();
	}
	
}
