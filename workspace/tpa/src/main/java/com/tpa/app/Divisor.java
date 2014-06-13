package com.tpa.app;

import java.util.ArrayList;
import java.util.PriorityQueue;

public interface Divisor {
	String nombre = "";
	ArrayList<Inscripcion> generarEquipoA(PriorityQueue<Inscripcion> priorityQueue); 
	ArrayList<Inscripcion> generarEquipoB(PriorityQueue<Inscripcion> inscripciones); 
}