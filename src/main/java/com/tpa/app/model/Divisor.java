package com.tpa.app.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Divisor {
	private String nombre;
	public abstract ArrayList<Inscripcion> generarEquipoA(List<Inscripcion> priorityQueue); 
	public abstract ArrayList<Inscripcion> generarEquipoB(List<Inscripcion> inscripciones);
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre;
	}
}