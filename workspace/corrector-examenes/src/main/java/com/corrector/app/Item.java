package main.java.com.corrector.app;

public class Item {
	public Pregunta pregunta;
	public String respuestaAlumno;
	
	public boolean esCorrecto() {
		return pregunta.esRespuestaCorrecta(respuestaAlumno);
	}
	
}
