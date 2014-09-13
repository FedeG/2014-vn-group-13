package com.tpa.app.ui;

import com.tpa.app.Jugador;
import com.uqbar.commons.collections.Transformer;

public final class PromedioTransformer implements Transformer<Jugador, Double> {
	@Override
	public Double transform(Jugador jugador) {
		if (jugador.getPartidosJugados().size() == 0){
			return 0.0;
		}
		Double sum = jugador.getPartidosJugados()
				.stream()
				.map(partido -> partido
					.getCalificaciones()
					.stream()
					.filter(calificacion -> calificacion.getJugador().equals(jugador))
					.mapToDouble(calificacion -> calificacion.getNota())
					.average()
					.getAsDouble()
				)
				.mapToDouble(promedio -> promedio)
				.sum();
		return sum.doubleValue()/(jugador.getPartidosJugados().size());
	}

}