package tst;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import corrector.examenes.Item;
import corrector.examenes.Pregunta;

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
		unItem.respuestaAlumno = "Roma";

		Assert.assertTrue(unItem.esCorrecto());
	}

	//Test #2 - Corrector - Item Incorrecto
	@Test
	public void testElItemEsIncorrecto() {
		unItem.respuestaAlumno = "Romulo";

		Assert.assertFalse(unItem.esCorrecto());
	}

}
