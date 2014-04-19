package tst;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import corrector.examenes.*;
import corrector.examenes.Parcial;
import corrector.examenes.Pregunta;

public class tstRoboprof {

	@Test
	public void corrigePorRestaYSaca7() {
		
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
		otroItem.respuestaAlumno = "Madrid";
		
		Roboprof unRoboprof = new Roboprof();
		
		Parcial unParcial = new Parcial();
		unParcial.items = new ArrayList<Item>();
		unParcial.items.add(otroItem);
		unParcial.items.add(unItem);
		
		Assert.assertEquals(7, unRoboprof.corregirConResta(unParcial, 3), 0);
	}
	
	@Test
	public void corrigePorReglaDeTresYSaca3() {
		
		Pregunta unaPregunta = new Pregunta();
		unaPregunta.descripcion = "¿Cual es la capital de Italia?";
		unaPregunta.respuestaCorrecta = "Roma";
		unaPregunta.pesoEspecifico = 10;
		Pregunta otraPregunta = new Pregunta();
		otraPregunta.descripcion = "¿Cual es la capital de España?";
		otraPregunta.respuestaCorrecta = "Madrid";
		otraPregunta.pesoEspecifico = 5;
		
		Item unItem = new Item();
		unItem.pregunta = unaPregunta;
		unItem.respuestaAlumno = "Romulo";
		Item otroItem = new Item();
		otroItem.pregunta = otraPregunta;
		otroItem.respuestaAlumno = "Madrid";
		
		Roboprof unRoboprof = new Roboprof();
		
		Parcial unParcial = new Parcial();
		unParcial.items = new ArrayList<Item>();
		unParcial.items.add(otroItem);
		unParcial.items.add(unItem);
		
		Assert.assertEquals(3, unRoboprof.corregirConReglaDeTres(unParcial), 0);
	}
	
	@Test
	public void corrigePorTablaYSaca5() {
		
		Pregunta unaPregunta = new Pregunta();
		unaPregunta.descripcion = "¿Cual es la capital de Italia?";
		unaPregunta.respuestaCorrecta = "Roma";
		unaPregunta.pesoEspecifico = 10;
		Pregunta otraPregunta = new Pregunta();
		otraPregunta.descripcion = "¿Cual es la capital de España?";
		otraPregunta.respuestaCorrecta = "Madrid";
		otraPregunta.pesoEspecifico = 5;
		
		Item unItem = new Item();
		unItem.pregunta = unaPregunta;
		unItem.respuestaAlumno = "Roma";
		Item otroItem = new Item();
		otroItem.pregunta = otraPregunta;
		otroItem.respuestaAlumno = "Madrid";
		
		Roboprof unRoboprof = new Roboprof();
		
		Parcial unParcial = new Parcial();
		unParcial.items = new ArrayList<Item>();
		unParcial.items.add(otroItem);
		unParcial.items.add(unItem);
		
		Assert.assertEquals(5, unRoboprof.corregirPorTabla(unParcial), 0);
	}
	
	@Test
	public void corrigeConNotaMasAltaYSaca() {
		
		Pregunta unaPregunta = new Pregunta();
		unaPregunta.descripcion = "¿Cual es la capital de Italia?";
		unaPregunta.respuestaCorrecta = "Roma";
		unaPregunta.pesoEspecifico = 10;
		Pregunta otraPregunta = new Pregunta();
		otraPregunta.descripcion = "¿Cual es la capital de España?";
		otraPregunta.respuestaCorrecta = "Madrid";
		otraPregunta.pesoEspecifico = 5;
		
		Item unItem = new Item();
		unItem.pregunta = unaPregunta;
		unItem.respuestaAlumno = "Roma";
		Item otroItem = new Item();
		otroItem.pregunta = otraPregunta;
		otroItem.respuestaAlumno = "Madrid";
		
		Roboprof unRoboprof = new Roboprof();
		
		Parcial unParcial = new Parcial();
		unParcial.items = new ArrayList<Item>();
		unParcial.items.add(otroItem);
		unParcial.items.add(unItem);
		
		Assert.assertEquals(10, unRoboprof.corregirTomandoNotaMasAlta(unParcial), 0);
	}
	
	@Test
	public void corrigeConPromedioYSaca8() {
		
		Pregunta unaPregunta = new Pregunta();
		unaPregunta.descripcion = "¿Cual es la capital de Italia?";
		unaPregunta.respuestaCorrecta = "Roma";
		unaPregunta.pesoEspecifico = 10;
		Pregunta otraPregunta = new Pregunta();
		otraPregunta.descripcion = "¿Cual es la capital de España?";
		otraPregunta.respuestaCorrecta = "Madrid";
		otraPregunta.pesoEspecifico = 5;
		
		Item unItem = new Item();
		unItem.pregunta = unaPregunta;
		unItem.respuestaAlumno = "Roma";
		Item otroItem = new Item();
		otroItem.pregunta = otraPregunta;
		otroItem.respuestaAlumno = "Madrid";
		
		Roboprof unRoboprof = new Roboprof();
		
		Parcial unParcial = new Parcial();
		unParcial.items = new ArrayList<Item>();
		unParcial.items.add(otroItem);
		unParcial.items.add(unItem);
		
		Assert.assertEquals(8, unRoboprof.corregirTomandoPromedio(unParcial), 0);
	}

}
