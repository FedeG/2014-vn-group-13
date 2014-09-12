package com.tpa.app.ui;

import java.util.stream.DoubleStream;

import com.tpa.app.Jugador;
import com.tpa.app.Partido;
import com.uqbar.commons.collections.Transformer;

/**
 * Transforma un booleano a un String, devolviendo "" en caso de ser verdadero
 * y "NO" en caso de ser falso.
 * 
 * @author npasserini
 */
public final class PromedioTransformer implements Transformer<Jugador, Double> {
	@Override
	public Double transform(Jugador jugador) {
		
		Double sum = (double) 0;
		
		if(jugador.getPartidosJugados().size() == 0) return 0.0;
		
		for (Partido partido : jugador.getPartidosJugados()) {
			
			Double nota = partido.getCalificaciones().stream().filter(calificacion -> calificacion.getJugador().equals(jugador)).mapToDouble(calificacion -> calificacion.getNota()).average()
					.getAsDouble();
			sum = sum + nota;
			
		}
		
		return sum/(jugador.getPartidosJugados().size());
	}

}