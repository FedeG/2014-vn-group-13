package com.tpa.app;

import java.util.ArrayList;

public interface Divisor {
	String nombre = "";
	ArrayList<Inscripcion> generarEquipoA(Inscripcion inscipcion); 
	ArrayList<Inscripcion> generarEquipoB(Inscripcion inscipcion); 
}