package com.tpa.app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;


public class GeneradorDeEquipos {

	public List<Inscripcion> ordenarJugadores(List<Criterio> list, Partido partido) {
		List<Inscripcion> inscOrdenadas = new ArrayList<Inscripcion>();
		inscOrdenadas.addAll(partido.getInscripciones());
		Collections.sort(inscOrdenadas, this.crearComparador(list, partido));
		return inscOrdenadas;
	}

	public void dividirEquipos(Divisor divisor, Partido partido, List<Inscripcion> inscripciones) {
		ArrayList<Inscripcion> equipoA, equipoB;
		equipoA = divisor.generarEquipoA(inscripciones);
		equipoB = divisor.generarEquipoB(inscripciones);
		partido.equiposAJugar(equipoA, equipoB);
	}

	public void generarEquipos(Partido partido, List<Criterio> criterios, Divisor divisor) {
		this.ordenarJugadores(criterios,	partido);
		this.dividirEquipos(divisor, partido, (List<Inscripcion>) partido.getInscripciones().stream().collect(Collectors.toList()));
	}

	public Comparator<Inscripcion> crearComparador(List<Criterio> list,
			Partido partido) {
		if (list.isEmpty())
			throw new RuntimeException(
					"No se han seleccionado criterios de ordenamiento.");
		Comparator<Inscripcion> comparator = (i1, i2) -> {
			OptionalDouble promedio1 = list.stream()
					.mapToDouble(criterio -> criterio.dameTuValor(i1))
					.average();
			OptionalDouble promedio2 = list.stream()
					.mapToDouble(criterio -> criterio.dameTuValor(i2))
					.average();
			return Double.compare(promedio2.getAsDouble(),
					promedio1.getAsDouble());
		};
		return comparator;
	}
}