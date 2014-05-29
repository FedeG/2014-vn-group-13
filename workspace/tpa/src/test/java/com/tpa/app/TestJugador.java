package com.tpa.app;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.tpa.app.Inscripcion.PrioridadesInscripciones;

public class TestJugador {

	Partido partido;
	Jugador jugador;
	Jugador jugadorConAmigos;
	Persona persona;
	Persona personaConAmigos;
	Administrador admin;
	Inscripcion inscripcionEstandar;
	Inscripcion inscripcionCondicional;
	Inscripcion inscripcionSolidaria;
	Inscripcion inscripcionEstandarConAmigos;
	@Mock
	MailSender mailSenderMock;
	private int i;

	@Before
	public void setUp() {
		LocalDateTime fecha_y_hora = LocalDateTime.now();
		mailSenderMock = mock(MailSender.class);
		partido = new Partido(fecha_y_hora, "Parque patricios", 10,
				mailSenderMock);
		LocalDateTime fechaNac = LocalDateTime.of(1991, 9, 26, 23, 25);
		admin = new Administrador(mailSenderMock);
		persona = new Persona(fechaNac, "ceciliazgr@gmail.com");
		personaConAmigos = new Persona(fechaNac, "ceciliazgr@gmail.com");
		ArrayList<Persona> amigos = new ArrayList<Persona>();
		amigos.add(persona);
		personaConAmigos.setAmigos(amigos);
		jugador = new Jugador(persona);
		jugadorConAmigos = new Jugador(personaConAmigos);
		inscripcionCondicional = new Inscripcion(jugador, PrioridadesInscripciones.CONDICIONAL,(
				Partido partido) -> partido.getLugar() == "Parque patricios");
		inscripcionEstandar = new Inscripcion(jugador, PrioridadesInscripciones.ESTANDAR, null);
		inscripcionSolidaria = new Inscripcion(jugador, PrioridadesInscripciones.SOLIDARIA, null);
		inscripcionEstandarConAmigos = new Inscripcion(jugadorConAmigos, PrioridadesInscripciones.ESTANDAR, null);

	}

	// Test #1 - Inscribirse a un Partido
	// Test #1.1 - Forma Estandar
	@Test
	public void testInscribirmeEstandarAUnPartido() {

		partido.inscribir(inscripcionEstandar);
		Assert.assertEquals(
				"Hay 1 jugadores estandars",
				1,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.getModalidad() == PrioridadesInscripciones.ESTANDAR)
						.count());
		Assert.assertEquals(
				"Hay 0 jugadores solidarios",
				0,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.getModalidad() == PrioridadesInscripciones.SOLIDARIA)
						.count());
		Assert.assertEquals(
				"Hay 0 jugadores condicionales",
				0,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.getModalidad() == PrioridadesInscripciones.CONDICIONAL)
						.count());
	}

	// Test #1.2 - Forma Solidaria
	@Test
	public void testInscribirmeSolidarioAUnPartido() {
		partido.inscribir(inscripcionSolidaria);
		Assert.assertEquals(
				"Hay 0 jugadores estandars",
				0,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.getModalidad() == PrioridadesInscripciones.ESTANDAR)
						.count());
		Assert.assertEquals(
				"Hay 1 jugadores solidarios",
				1,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.getModalidad() == PrioridadesInscripciones.SOLIDARIA)
						.count());
		Assert.assertEquals(
				"Hay 0 jugadores condicionales",
				0,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.getModalidad() == PrioridadesInscripciones.CONDICIONAL)
						.count());
	}

	// Test #1.3 - Forma Condicional
	@Test
	public void testInscribirmeCondicionalAUnPartido() {
		partido.inscribir(inscripcionCondicional);
		Assert.assertEquals(
				"Hay 0 jugadores estandars",
				0,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.getModalidad() == PrioridadesInscripciones.ESTANDAR)
						.count());
		Assert.assertEquals(
				"Hay 0 jugadores solidarios",
				0,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.getModalidad() == PrioridadesInscripciones.SOLIDARIA)
						.count());
		Assert.assertEquals(
				"Hay 1 jugadores condicionales",
				1,
				partido.getInscripciones()
						.stream()
						.filter(i -> i.getActivo()
								&& i.getModalidad() == PrioridadesInscripciones.CONDICIONAL)
						.count());
	}

	// Test #2 - Notificar amigos al inscribirse a un Partido

	@Test
	public void testAvisarAmigosAlInscribirmeAPartido() {
		partido.inscribir(inscripcionEstandarConAmigos);
		verify(mailSenderMock, times(1)).enviarMail(any(Mail.class));
	}

	// Test #3 - Proponer jugador

	@Test
	public void testProponerJugador() {
		jugador.proponer(personaConAmigos, partido, admin,
				PrioridadesInscripciones.ESTANDAR);
		Assert.assertEquals("La cantidad de propuestas del admin es 1", 1,
				admin.getPropuestas().size());
		Assert.assertEquals(admin.getPropuestas().get(0).getPersona(),
				personaConAmigos);
		Assert.assertEquals(admin.getPropuestas().get(0).getPartido(), partido);
		Assert.assertEquals(admin.getPropuestas().get(0).getModalidad(),
				PrioridadesInscripciones.ESTANDAR);

	}

	// Test #4 - Darse de baja con reemplazo
	

	// Test #5 - Darse de baja sin reemplazo y quedan menos de 10 jugadores (debe notificarse al admin)

	
	// Test #6 - Calificar a un jugador correcto

	@Test
	public void testCalificarJugadorCorrecto() {

		partido.inscribir(inscripcionEstandar);
		jugador.calificar(jugador, partido, 10, "soy el mejor del mundo");
		Assert.assertEquals(partido.getCalificaciones().get(0).getNota(), 10);
		Assert.assertEquals(partido.getCalificaciones().get(0).getJugador(),
				jugador);
		Assert.assertEquals(partido.getCalificaciones().get(0).getCritica(),
				"soy el mejor del mundo");

	}

	// Test #7 - Calificar a un jugador incorrecto

	@Test
	public void testCalificarJugadorIncorrecto() {

		i = jugador.calificar(jugador, partido, 3, "pésimo!!!!");
		Assert.assertEquals(i, 1);
		Assert.assertTrue(partido.getCalificaciones().stream()
				.noneMatch(c -> c.getNota() == 3));

	}

}
