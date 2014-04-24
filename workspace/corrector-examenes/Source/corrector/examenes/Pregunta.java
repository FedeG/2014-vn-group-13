package corrector.examenes;

public class Pregunta {
	public int pesoEspecifico;
	public String respuestaCorrecta;
	public String descripcion;
	
	public boolean esRespuestaCorrecta(String respuestaAlumno){
		return pregunta.respuestaCorrecta.equalsIgnoreCase(respuestaAlumno);
	}
}

