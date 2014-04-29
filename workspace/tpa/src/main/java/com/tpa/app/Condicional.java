package com.tpa.app;

import java.util.function.Predicate;

public class Condicional implements TipoJugador{
	private Predicate<Partido> condicion;

	public Predicate<Partido> getCondicion() {
		return this.condicion;
	}

	public void setCondicion(Predicate<Partido> condicion) {
		this.condicion = condicion;
	}

	public boolean PuedeJugar(Partido partido) {
		// TODO Auto-generated method stub
		return false; //Depende las condiciones
	}

}
