package com.tpa.app;

import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.tpa.app.Inscripcion.PrioridadesInscripciones;

public class TestJugador {

	Partido partido;
	Jugador jugador;
	Persona persona;
	InscripcionEstandar inscripcionEstandar;
	InscripcionCondicional inscripcionCondicional;
	InscripcionSolidaria inscripcionSolidaria;
	@Mock
	MailSender mailSenderMock;

	@Before
	public void setUp() {
		LocalDateTime fecha_y_hora = LocalDateTime.now();
		mailSenderMock = mock(MailSender.class);
		partido = new Partido(fecha_y_hora, "Parque patricios", 10,
				mailSenderMock);
		LocalDateTime fechaNac = LocalDateTime.of(1991, 9, 26, 23, 25);
		persona = new Persona(fechaNac, "ceciliazgr@gmail.com");
		jugador = new Jugador(persona);
		inscripcionCondicional = new InscripcionCondicional(jugador, (
				Partido partido) -> partido.getLugar() == "Parque patricios");
		inscripcionEstandar = new InscripcionEstandar(jugador);
		inscripcionSolidaria = new InscripcionSolidaria(jugador);
	}
	
	//Test #1 - Inscribirse a un Partido
	//Test #1.1 - Forma Estandar	
	@Test
	public void testInscribirmeEstandarAUnPartido() {

		partido.inscribir(inscripcionEstandar);
		Assert.assertEquals(
				"Hay 1 jugadores estandars",
				1,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.dameTuPrioridad() == PrioridadesInscripciones.Estandar)
						.count());
		Assert.assertEquals(
				"Hay 0 jugadores solidarios",
				0,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.dameTuPrioridad() == PrioridadesInscripciones.Solidaria)
						.count());
		Assert.assertEquals(
				"Hay 0 jugadores condicionales",
				0,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.dameTuPrioridad() == PrioridadesInscripciones.Condicional)
						.count());
	}

	
	//Test #1.2 - Forma Solidaria
	@Test
	public void testInscribirmeSolidarioAUnPartido() {
		partido.inscribir(inscripcionSolidaria);
		Assert.assertEquals(
				"Hay 0 jugadores estandars",
				0,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.dameTuPrioridad() == PrioridadesInscripciones.Estandar)
						.count());
		Assert.assertEquals(
				"Hay 1 jugadores solidarios",
				1,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.dameTuPrioridad() == PrioridadesInscripciones.Solidaria)
						.count());
		Assert.assertEquals(
				"Hay 0 jugadores condicionales",
				0,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.dameTuPrioridad() == PrioridadesInscripciones.Condicional)
						.count());
	}

	//Test #1.3 - Forma Condicional
	@Test
	public void testInscribirmeCondicionalAUnPartido() {
		partido.inscribir(inscripcionCondicional);
		Assert.assertEquals(
				"Hay 0 jugadores estandars",
				0,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.dameTuPrioridad() == PrioridadesInscripciones.Estandar)
						.count());
		Assert.assertEquals(
				"Hay 0 jugadores solidarios",
				0,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.dameTuPrioridad() == PrioridadesInscripciones.Solidaria)
						.count());
		Assert.assertEquals(
				"Hay 1 jugadores condicionales",
				1,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.dameTuPrioridad() == PrioridadesInscripciones.Condicional)
						.count());
	}

	//Test #2 - Notificar amigos al inscribirse a un Partido
		
	@Test
	public void testAvisarAmigosAlInscribirmeAPartido() {

	}
}
