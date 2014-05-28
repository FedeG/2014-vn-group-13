package com.tpa.app;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

;

public class TestPartido {

	Partido partido;
	Partido partido2;
	@Mock
	MailSender mailSenderMock;

	Jugador jugador;
	Persona persona;

	@Before
	public void setUp() {
		LocalDateTime fecha_y_hora = LocalDateTime.now();
		mailSenderMock = mock(MailSender.class);

		partido = new Partido(fecha_y_hora, "Parque Patricios", 10,
				mailSenderMock);
		partido2 = new Partido(fecha_y_hora, "Parque Patricios", 10,
				mailSenderMock);
		LocalDateTime fechaNac = LocalDateTime.of(1991, 9, 26, 23, 25);
		persona = new Persona(fechaNac, "ceciliazgr@gmail.com");
		jugador = new Jugador(persona);

	}

	// Test #1 - Inscribir jugadores y notificar administrador
	// Test #1.1 - Inscribir 10 o más jugadores con cualquier modalidad y que se
	// notifique al administrador una vez por cada inscripción que supera o
	// iguala los 10 inscriptos
	@Test
	public void testNotificarAlAdministrador() {
		for (int i = 0; i < 10; i++)
			partido.inscribir(new InscripcionEstandar(jugador));
		for (int i = 0; i < 5; i++)
			partido.inscribir(new InscripcionSolidaria(jugador));
		for (int i = 0; i < 8; i++)
			partido.inscribir(new InscripcionCondicional(jugador, null));
		verify(mailSenderMock, times(14)).enviarMail(any(Mail.class));
	}

	// Test #1.2 - Inscribir menos de 10 jugadores con cualquier modalidad y que
	// no se notifique al administrador
	@Test
	public void testNoNotificarAlAdministrador() {
		for (int i = 0; i < 8; i++)
			partido2.inscribir(new InscripcionEstandar(jugador));
		verify(mailSenderMock, times(0)).enviarMail(any(Mail.class));
	}
}
