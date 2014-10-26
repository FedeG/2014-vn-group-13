package com.tpa.db;

import static com.tpa.app.db.EntityManagerHelper.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.tpa.app.db.EntityManagerHelper;
import com.tpa.app.model.Administrador;
import com.tpa.app.model.Infraccion;
import com.tpa.app.model.Jugador;
import com.tpa.app.model.Partido;

public class ContextTest {

	@Test
	public void contextUp() {
		EntityManagerHelper.getEntityManager();
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		EntityManagerHelper.withTransaction(() -> {});
	}
	
	@After
	   public void tearDown() throws Exception {
	      EntityManagerHelper.rollback();
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void ListaJugadoresNoVacia() {
		
		List<Jugador> jugadoresPersistidos = getEntityManager()
				.createQuery("from Jugador")
				.getResultList();
		
		assertTrue(!jugadoresPersistidos.isEmpty());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void LevantarLosAdmins() {
		
		List<Administrador> administradores = getEntityManager()
				.createQuery("from Administrador")
				.getResultList();
		
		if (administradores == null)
			try { throw new Exception("Debe haber un admin en la BD para realizar este test");
			} catch (Exception e) {	e.printStackTrace(); }
		
		assertTrue(!administradores.isEmpty());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void LevantarLosPartidos() {
		
		List<Partido> partidosPersistidos = getEntityManager()
				.createQuery("from Partido")
				.getResultList();
		
		assertTrue(!partidosPersistidos.isEmpty());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void LevantarLosPartidosPendientesDeConfirmarDeUnAdmin() {
		
		List<Administrador> administradores = getEntityManager()
				.createQuery("from Administrador")
				.getResultList();
		
		if (administradores == null || administradores.isEmpty())
			try { throw new Exception("Debe haber un admin en la BD para realizar este test");
			} catch (Exception e) {	e.printStackTrace(); }
		
		Administrador admin = administradores.get(0);
		
		List<Partido> partidosPersistidos = 
				EntityManagerHelper
				.createQuery(String.format("from Partido where administrador.id = :idAdmin and confirmado = false"))
				.setParameter("idAdmin", admin.getId())
				.getResultList();
		
		assertTrue(!partidosPersistidos.isEmpty());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void LevantarJugadorConInfracciones() {
		
		List<Infraccion> infracciones = getEntityManager()
				.createQuery(" from Infraccion")
				.getResultList();
		
		if (infracciones == null)
			try { throw new Exception("Debe haber una infraccion en la BD para realizar este test");
			} catch (Exception e) {	e.printStackTrace(); }
		
		Infraccion infraccion = infracciones.get(0);
		
		assertTrue(!infraccion.getJugador().getInfracciones().isEmpty());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void LevantarLasInscripciones()
	{
		List<Partido> partidos = getEntityManager()
				.createQuery(" from Partido")
				.getResultList();
		
		List<Partido> conInscripciones = partidos.stream()
				.filter(p -> p.getInscripciones().size() > 0)
				.collect(Collectors.toList());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void PersistirEquiposAlConfirmarUnPartido()
	{
		List<Administrador> administradores = getEntityManager()
				.createQuery("from Administrador")
				.getResultList();
		
		if (administradores == null || administradores.isEmpty())
			try { throw new Exception("Debe haber un admin en la BD para realizar este test");
			} catch (Exception e) {	e.printStackTrace(); }
		
		Predicate<Administrador> tienenPendientes = new Predicate<Administrador> (){
			public boolean apply(Administrador a) {
	            return a.getPartidosPendientes().size() > 0;
	        }
		};
		
		Administrador admin = Iterables.find(administradores, tienenPendientes, null);
		
		if (admin == null)
			try { throw new Exception("No hay admins con posibilidad de confirmar un partido");
			} catch (Exception e) {	e.printStackTrace(); }
		
		
	
	}
}
