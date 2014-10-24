package com.tpa.app.model;

import java.util.List;

public class PorPromedio extends Criterio {
	private String nombre = "Por Promedio"; // Promedio de calificaciones del
											// ultimo partido jugado

	public String getNombre() {
		return nombre;
	}

	@Override
	public double dameTuValor(Inscripcion inscripcion) {
		List<Partido> partidosJugados = inscripcion.getJugador()
				.getPartidosJugados();
		if (partidosJugados.isEmpty())
			return 0.0;
		Partido ultimoPartido = partidosJugados.get(0);
		double valor = ultimoPartido
				.getCalificaciones()
				.stream()
				.filter(calificacion -> calificacion.getJugadorCalificado().equals(
						inscripcion.getJugador()))
				.mapToDouble(calificacion -> calificacion.getNota()).average()
				.getAsDouble();
		return valor;

	}
}
