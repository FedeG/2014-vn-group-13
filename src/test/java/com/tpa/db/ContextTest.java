package com.tpa.db;

import static com.tpa.app.db.EntityManagerHelper.getEntityManager;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import com.tpa.app.db.EntityManagerHelper;
import com.tpa.app.model.Administrador;
import com.tpa.app.model.Jugador;
import com.tpa.app.model.Partido;
import com.tpa.app.repo.RepositorioJugadores;

public class ContextTest {

	@Test
	public void contextUp() {
		EntityManagerHelper.getEntityManager();
	}

	@Test
	@SuppressWarnings("unchecked")
	public void ListaJugadoresNoVacia() {
		
		List<Jugador> jugadoresPersistidos = getEntityManager()
				.createQuery("from Jugador")
				.getResultList();
		
		assertTrue(!jugadoresPersistidos.isEmpty());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void LevantarElAdmin() {
		
		Administrador admin = (Administrador)getEntityManager()
				.createQuery("from Administrador")
				.getResultList().get(0);
		
		assertTrue(admin != null);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void LevantarLosPartidos() {
		
		List<Partido> partidosPersistidos = getEntityManager()
				.createQuery("from Partidos")
				.getResultList();
		
		assertTrue(!partidosPersistidos.isEmpty());
	}
	
	@Test
	public void contextUpWithTransaction() throws Exception {
		EntityManagerHelper.withTransaction(() -> {});
	}
}
