package ar.edu.futbol5;

import ar.edu.futbol5.inscripcion.CriterioInscripcion;
import ar.edu.futbol5.inscripcion.ModoSolidario;

import java.util.ArrayList;
import java.util.List;

import ar.edu.futbol5.inscripcion.ModoEstandar;

public class Jugador {

	private String nombre;
	private Double calificacion;
	private List<Double> puntajes;
	CriterioInscripcion criterioInscripcion;

	public Jugador(CriterioInscripcion criterioInscripcion) {
		this(criterioInscripcion,"", 0, new ArrayList<Double>() );
	}

	public Jugador(CriterioInscripcion criterioInscripcion, String nombre,
			double calificacion, List<Double> puntajes) {
		this.calificacion = calificacion;
		this.puntajes = puntajes;
		this.criterioInscripcion = criterioInscripcion;
		this.nombre = nombre;
	}

	boolean dejaLugarAOtro() {
		return this.criterioInscripcion.dejaLugarAOtro();
	}

	public String toString() {
		// "Jugador (" + calificacion + ") - modo " +
		// criterioInscripcion.toString()
		return nombre;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public List<Double> getPuntajes() {
		return puntajes;
	}
}
