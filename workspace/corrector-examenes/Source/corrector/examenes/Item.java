package corrector.examenes;

public class Item {
	public Pregunta pregunta;
	public String respuestaAlumno;
	
	public boolean esCorrecto() {
		if (pregunta.respuestaCorrecta.compareTo(respuestaAlumno) == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
