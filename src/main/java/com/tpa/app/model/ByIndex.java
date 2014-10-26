package com.tpa.app.model;

import java.util.ArrayList;
import java.util.List;

public class ByIndex extends Divisor {
	private ArrayList<Integer> indicesEquipoA;
	private ArrayList<Integer> indicesEquipoB;

	public ByIndex(ArrayList<Integer> indicesEquipoA, ArrayList<Integer> indicesEquipoB) {
		this.setIndicesEquipoA(indicesEquipoA);
		this.setIndicesEquipoB(indicesEquipoB);
	}

	public ByIndex(String nombre, ArrayList<Integer> indicesEquipoA, ArrayList<Integer> indicesEquipoB) {
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

	@Override
	public ArrayList<Inscripcion> generarEquipoA(List<Inscripcion> inscripciones) {
		return generarEquipoConIndices(inscripciones, this.getIndicesEquipoA());
	}

	@Override
	public ArrayList<Inscripcion> generarEquipoB(List<Inscripcion> inscripciones) {
		return generarEquipoConIndices(inscripciones, this.getIndicesEquipoB());
	}

	public ArrayList<Inscripcion> generarEquipoConIndices(List<Inscripcion> inscripciones, ArrayList<Integer> indices) {
		ArrayList<Inscripcion> retorno = new ArrayList<Inscripcion>();
		ArrayList<Inscripcion> inscripcionesList = new ArrayList<>();
		inscripcionesList.addAll(inscripciones);
		indices.forEach((numero) -> retorno.add( inscripcionesList
				.get(numero)));
		return retorno;
	}
}