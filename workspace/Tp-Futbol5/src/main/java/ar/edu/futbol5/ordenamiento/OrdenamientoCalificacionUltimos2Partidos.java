package ar.edu.futbol5.ordenamiento;

import java.util.ArrayList;
import java.util.List;

import ar.edu.futbol5.Jugador;

public class OrdenamientoCalificacionUltimos2Partidos extends CriterioOrdenamiento {	

	public Double calcularValor(Jugador jugador) {
		List<Double> puntajes=jugador.getPuntajes();
		List<Double> misPuntajes=new ArrayList<Double>(); 
		if(!puntajes.isEmpty()){
			misPuntajes.add(jugador.getPuntajes().get(puntajes.size()-1));
		}
		if(puntajes.size()>1){
			misPuntajes.add(jugador.getPuntajes().get(puntajes.size()-2));
		}
		
		return misPuntajes.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
	}
	
}
