package com.tpa.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.mockito.Mock;

import com.tpa.app.Inscripcion.PrioridadesInscripciones;
import com.tpa.app.Propuesta.EstadoPropuesta;

public class TestPropuesta {

	Persona persona;
	Propuesta propuesta;
	Partido partido;
	Administrador admin;
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
		propuesta = new Propuesta(persona, PrioridadesInscripciones.ESTANDAR,
				partido);
		admin = new Administrador(mailSenderMock);
	}

	// Test #1 - Aceptar una propuesta

	@Test
	public void testAceptarPropuesta() {
		propuesta.estasAprobada();
		Assert.assertEquals(EstadoPropuesta.Aprobada, propuesta.getEstado());
		Assert.assertEquals(partido.getInscripciones().size(), 1);
		Assert.assertEquals(
				true,
				partido.getInscripciones().stream()
						.anyMatch(i -> i.getJugador().getPersona().equals(persona)));
	}

	// Test #2 - Rechazar una propuesta

	@Test
	public void testRechazarPropuesta() {
		propuesta
				.estasRechazada("porque el jugador es malísimo y además le gustan las pantallas táctiles");
		Assert.assertTrue("El estado es rechazado", propuesta.getEstado()
				.equals(EstadoPropuesta.Rechazada));
		Assert.assertTrue(
				"El motivo es el que dí",
				propuesta
						.getMotivo()
						.compareTo(
								"porque el jugador es malísimo y además le gustan las pantallas táctiles") == 0);
	}

}
