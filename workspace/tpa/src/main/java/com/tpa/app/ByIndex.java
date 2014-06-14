package com.tpa.app;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ByIndex implements Divisor {
	private ArrayList<Integer> indicesEquipoA;
	private ArrayList<Integer> indicesEquipoB;
	private String nombre = "Divisor por Index";

	public ByIndex(ArrayList<Integer> indicesEquipoA,
			ArrayList<Integer> indicesEquipoB) {
		this.setIndicesEquipoA(indicesEquipoA);
		this.setIndicesEquipoB(indicesEquipoB);
	}

	public ByIndex(String nombre, ArrayList<Integer> indicesEquipoA,
			ArrayList<Integer> indicesEquipoB) {
		this(indicesEquipoA, indicesEquipoB);
		this.setNombre(nombre);
	}

	public ArrayList<Integer> getIndicesEquipoA() {
		return indicesEquipoA;
	}

	public void setIndicesEquipoA(ArrayList<Integer> indicesEquipoA) {
		this.indicesEquipoA = indicesEquipoA;
	}

	public ArrayList<Integer> getIndicesEquipoB() {
		return indicesEquipoB;
	}

	public void setIndicesEquipoB(ArrayList<Integer> indicesEquipoB) {
		this.indicesEquipoB = indicesEquipoB;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public ArrayList<Inscripcion> generarEquipoA(
			PriorityQueue<Inscripcion> inscripciones) {
		return generarEquipoConIndices(inscripciones, this.getIndicesEquipoA());
	}

	@Override
	public ArrayList<Inscripcion> generarEquipoB(
			PriorityQueue<Inscripcion> inscripciones) {
		return generarEquipoConIndices(inscripciones, this.getIndicesEquipoB());
	}

	public ArrayList<Inscripcion> generarEquipoConIndices(
			PriorityQueue<Inscripcion> inscripciones, ArrayList<Integer> indices) {
		ArrayList<Inscripcion> retorno = new ArrayList<Inscripcion>();
		ArrayList<Inscripcion> inscripcionesList = new ArrayList<>();
		inscripcionesList.addAll(inscripciones);
		indices.forEach((numero) -> retorno.add( inscripcionesList
				.get(numero)));
		return retorno;
	}
}