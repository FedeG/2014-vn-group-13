package ar.edu.futbol5.ordenamiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.edu.futbol5.Jugador;
import ar.edu.futbol5.Partido;

public class OrdenamientoPorHandicap extends CriterioOrdenamiento {
	
	public Double calcularValor(Jugador jugador) {
		return jugador.getCalificacion();
	}
	
}
