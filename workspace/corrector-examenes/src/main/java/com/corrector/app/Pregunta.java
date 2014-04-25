package com.corrector.app;

public class Pregunta {
	public int pesoEspecifico;
	public String respuestaCorrecta;
	public String descripcion;
	
	public boolean esRespuestaCorrecta(String respuestaAlumno){
		return respuestaCorrecta.equalsIgnoreCase(respuestaAlumno);
	}
}

