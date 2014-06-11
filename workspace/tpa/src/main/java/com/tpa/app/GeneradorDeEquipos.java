package com.tpa.app;

import java.util.ArrayList;

public class GeneradorDeEquipos {

	public void ordenarJugadores(ArrayList<Criterio> criterios, Partido partido){
		
	}

	public void dividirEquipos(Divisor divisor, Partido partido){
		ArrayList<Inscripcion> equipoA, equipoB;
		equipoA = divisor.generarEquipoA(partido.getInscripciones());
		equipoB = divisor.generarEquipoB(partido.getInscripciones());
		partido.equiposAJugar(equipoA, equipoB);
	}

}