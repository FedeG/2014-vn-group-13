package test.java.com.corrector.app;

import main.java.com.corrector.app.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestParcial {
	
	private Parcial parcial;
	
	@Before
	public void setUp() {
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
				
		parcial = new Parcial();
		parcial.items = new ArrayList<Item>();
		parcial.items.add(otroItem);
		parcial.items.add(unItem);
	}

	@Test
	public void testElParcialTienePuntaje10() {
		Assert.assertEquals(10, parcial.puntajeTotal(), 0);
	}
	
	@Test
	public void testElAlumnoJunto7Puntos() {
		Assert.assertEquals(7, parcial.puntajeAlumno(), 0);
	}

}
