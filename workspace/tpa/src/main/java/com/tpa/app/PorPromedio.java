package com.tpa.app;

import java.util.List;

public class PorPromedio implements Criterio {
	private String nombre = "Promedio de calificaciones del último partido jugado";

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
				.filter(calificacion -> calificacion.getJugador().equals(
						inscripcion.getJugador()))
				.mapToDouble(calificacion -> calificacion.getNota()).average()
				.getAsDouble();
		return valor;

	}
}
