package test.java.com.corrector.app;

import main.java.com.corrector.app.Item;
import main.java.com.corrector.app.Pregunta;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestItem {
	
	private Item item;
	
	@Before
	public void setUp() {
		Pregunta unaPregunta = new Pregunta();
		unaPregunta.descripcion = "¿Cual es la capital de Italia?";
		unaPregunta.respuestaCorrecta = "Roma";
		
		item = new Item();
		item.pregunta = unaPregunta;
	}

	//Test #1 - Corrector - Item Correcto
	@Test
	public void testElItemEsCorrecto() {
		item.respuestaAlumno = "Roma";

		Assert.assertTrue(item.esCorrecto());
	}

	//Test #2 - Corrector - Item Incorrecto
	@Test
	public void testElItemEsIncorrecto() {
		item.respuestaAlumno = "Romulo";

		Assert.assertFalse(item.esCorrecto());
	}

}
