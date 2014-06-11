package com.tpa.app;

import java.util.ArrayList;

public interface Divisor {
	String nombre = "";
	ArrayList<Inscripcion> generarEquipoA(ArrayList<Inscripcion> inscripciones); 
	ArrayList<Inscripcion> generarEquipoB(ArrayList<Inscripcion> inscripciones); 
}