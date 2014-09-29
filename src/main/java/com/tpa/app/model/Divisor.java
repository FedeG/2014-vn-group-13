package com.tpa.app.model;

import java.util.ArrayList;
import java.util.List;

public interface Divisor {
	String nombre = "";
	ArrayList<Inscripcion> generarEquipoA(List<Inscripcion> priorityQueue); 
	ArrayList<Inscripcion> generarEquipoB(List<Inscripcion> inscripciones); 
}