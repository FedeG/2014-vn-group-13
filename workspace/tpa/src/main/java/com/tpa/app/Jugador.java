package com.tpa.app;

import java.util.function.Predicate;

public class Jugador {
	private int inasistencias = 0;
	private int edad;
	private Predicate<Partido> condicion;

	public Jugador(int edad) {
		this.setCondicion((Partido partido) -> true);
		this.setEdad(edad);
	}

	public Jugador(int edad, Predicate<Partido> condicion) {
		this.setCondicion(condicion);
		this.setEdad(edad);
	}

	public int getInasistencias() {
		return this.inasistencias;
	}

	public Predicate<Partido> getCondicion() {
		return this.condicion;
	}

	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setCondicion(Predicate<Partido> condicion) {
		this.condicion = condicion;
	}

}
