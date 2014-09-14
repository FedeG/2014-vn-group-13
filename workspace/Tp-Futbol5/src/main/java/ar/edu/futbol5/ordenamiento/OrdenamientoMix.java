package ar.edu.futbol5.ordenamiento;

import java.util.ArrayList;
import java.util.List;

import ar.edu.futbol5.Jugador;

public class OrdenamientoMix extends CriterioOrdenamiento {
	
	List<CriterioOrdenamiento> criterios;
	
	public OrdenamientoMix() {
		criterios = new ArrayList<CriterioOrdenamiento>();
	}
	
	public void addCriterio(CriterioOrdenamiento criterio) {
		criterios.add(criterio);
	}
	
	public Double calcularValor(Jugador jugador) {
		return criterios.stream().mapToDouble(criterio -> criterio.calcularValor(jugador)).sum();
	}
	
}
