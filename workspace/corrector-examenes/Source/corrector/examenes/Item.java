package corrector.examenes;

public class Item {
	public Pregunta pregunta;
	public String respuestaAlumno;
	
	public boolean esCorrecto() {
		return pregunta.respuestaCorrecta.compareTo(respuestaAlumno) == 0;
	}
	
}
