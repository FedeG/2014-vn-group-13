package com.corrector.app;

import org.junit.Assert;
import org.junit.Test;

public class TestItem {
	
	//Test #1 - Corrector - Item Correcto
	
	@Test
	public void ElItemEsCorrecto() {
		Pregunta unaPregunta = new Pregunta();
		unaPregunta.descripcion = "¿Cual es la capital de Italia?";
		unaPregunta.respuestaCorrecta = "Roma";
		
		Item unItem = new Item();
		unItem.pregunta = unaPregunta;
		unItem.respuestaAlumno = "Roma";
		
		Assert.assertTrue(unItem.esCorrecto());
	}

	//Test #2 - Corrector - Item Incorrecto
	@Test
	public void ElItemEsIncorrecto() {
		Pregunta unaPregunta = new Pregunta();
		unaPregunta.descripcion = "¿Cual es la capital de Italia?";
		unaPregunta.respuestaCorrecta = "Roma";
		
		Item unItem = new Item();
		unItem.pregunta = unaPregunta;
		unItem.respuestaAlumno = "Romulo";
		
		Assert.assertFalse(unItem.esCorrecto());
	}
}
