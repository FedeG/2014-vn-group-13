package tst;

import corrector.examenes.*;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class tstParcial {

	@Test
	public void elParcialTienePuntaje10() {
		
		Pregunta unaPregunta = new Pregunta();
		unaPregunta.descripcion = "¿Cual es la capital de Italia?";
		unaPregunta.respuestaCorrecta = "Roma";
		unaPregunta.pesoEspecifico = 7;
		Pregunta otraPregunta = new Pregunta();
		otraPregunta.descripcion = "¿Cual es la capital de España?";
		otraPregunta.respuestaCorrecta = "Madrid";
		otraPregunta.pesoEspecifico = 3;
		
		Item unItem = new Item();
		unItem.pregunta = unaPregunta;
		unItem.respuestaAlumno = "Roma";
		Item otroItem = new Item();
		otroItem.pregunta = otraPregunta;
		otroItem.respuestaAlumno = "Barcelona";
		
		
		
		Parcial unParcial = new Parcial();
		unParcial.items = new ArrayList<Item>();
		unParcial.items.add(otroItem);
		unParcial.items.add(unItem);
		
		Assert.assertEquals(10, unParcial.puntajeTotal(), 0);
	}
	
	@Test
	public void elAlumnoJunto7Puntos() {
		Pregunta unaPregunta = new Pregunta();
		unaPregunta.descripcion = "¿Cual es la capital de Italia?";
		unaPregunta.respuestaCorrecta = "Roma";
		unaPregunta.pesoEspecifico = 7;
		Pregunta otraPregunta = new Pregunta();
		otraPregunta.descripcion = "¿Cual es la capital de España?";
		otraPregunta.respuestaCorrecta = "Madrid";
		otraPregunta.pesoEspecifico = 3;
		
		Item unItem = new Item();
		unItem.pregunta = unaPregunta;
		unItem.respuestaAlumno = "Roma";
		Item otroItem = new Item();
		otroItem.pregunta = otraPregunta;
		otroItem.respuestaAlumno = "Barcelona";
		
		Parcial unParcial = new Parcial();
		unParcial.items = new ArrayList<Item>();
		unParcial.items.add(otroItem);
		unParcial.items.add(unItem);
		
		Assert.assertEquals(7, unParcial.puntajeAlumno(), 0);
	}

}
