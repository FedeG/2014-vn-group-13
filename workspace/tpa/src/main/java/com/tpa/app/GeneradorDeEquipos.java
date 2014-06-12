package com.tpa.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GeneradorDeEquipos {

	public List<Inscripcion> ordenarJugadores(List<Criterio> list,
			Partido partido) {

		List<Inscripcion> inscOrdenadas = new ArrayList<Inscripcion>();
		inscOrdenadas.addAll(partido.getInscripciones());
		Collections.sort(inscOrdenadas, this.crearComparador(list, partido));
		return inscOrdenadas;

	}

	public void dividirEquipos(Divisor divisor, Partido partido) {
		ArrayList<Inscripcion> equipoA, equipoB;
		equipoA = divisor.generarEquipoA(partido.getInscripciones());
		equipoB = divisor.generarEquipoB(partido.getInscripciones());
		partido.equiposAJugar(equipoA, equipoB);
	}

	public Comparator<Inscripcion> crearComparador(List<Criterio> list,
			Partido partido) {

		Comparator<Inscripcion> comparator = (i1, i2) -> {

			int total = 0, promedio1 = 0, promedio2 = 0;
			for (Criterio i : list) {
				total += +i.dameTuValor(i1);
			}
			;
			promedio1 = total / list.size();
			total = 0;
			for (Criterio i : list) {
				total += i.dameTuValor(i2);
			}
			;
			promedio2 = total / list.size();

			return promedio2 - promedio1;
		};
		return comparator;
	}
}