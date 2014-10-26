package com.tpa.app.repo;

//import static org.mockito.Mockito.mock;

import static com.tpa.app.db.EntityManagerHelper.getEntityManager;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.db.EntityManagerHelper;
import com.tpa.app.model.Administrador;
import com.tpa.app.model.Inscripcion;
import com.tpa.app.model.Partido;
import com.tpa.app.model.PartidoMailSender;
import com.tpa.app.model.Persona;

@SuppressWarnings("serial")
@Observable
public class RepositorioPartidos implements Serializable{

	//@Mock
	//MailSender mailSenderMock;
	
	private Administrador administrador;
	//private List<Partido> data = new ArrayList<Partido>();
	private static final RepositorioPartidos instance = new RepositorioPartidos();
	public static RepositorioPartidos getInstance() {
	    //if (instance == null) instance = new RepositorioPartidos();
		return instance;
	}

	public RepositorioPartidos()  {
		Timestamp fecha_y_hora = Timestamp.from(Instant.now());
		Administrador admin = new Administrador(new Persona(fecha_y_hora, "admin_lomas@futbol.com", "admin"), new PartidoMailSender());
		
		admin = (Administrador)getEntityManager()
				.createQuery("from Administrador")
				.getResultList().get(0);
		
		if (admin == null)
			try { throw new Exception("Debe haber un admin en la base de datos");
			} catch (Exception e) {	e.printStackTrace(); }
		
		this.administrador = admin;
	}
	
	// ********************************************************
	// ** Getter
	// ********************************************************
		
	@SuppressWarnings("unchecked")
	public List<Partido> getData(){
		
		if (this.administrador == null)
			try { throw new Exception("Administrador no seteado");
			} catch (Exception e) {	e.printStackTrace(); }
		
		return EntityManagerHelper
				.createQuery(String.format("from Partido where administrador.id = :idAdmin and confirmado = false"))
				.setParameter("idAdmin", this.administrador.getId())
				.getResultList();
	}
	
	public Administrador getAdministrador(){
		return this.administrador;
	}

	// ********************************************************
	// ** Altas
	// ********************************************************

	public void create(Partido partido) {
		//this.data.add(partido);
		EntityManagerHelper.persist(partido);
	}

	// ********************************************************
	// ** Búsquedas
	// ********************************************************


	public List<Partido> search() {
		return getData();
	}

	public List<Persona> searchJugadoresEquipoA(Partido partidoSeleccionado) {
		List<Persona> resultados = new ArrayList<Persona>();
		if (partidoSeleccionado != null) {
			for (Inscripcion ins : partidoSeleccionado.getEquipoA()){
				resultados.add(ins.getJugador().getPersona());
			}
		}
		
		return resultados;
	}
	
	public List<Persona> searchJugadoresEquipoB(Partido partidoSeleccionado) {
		List<Persona> resultados = new ArrayList<Persona>();
		if (partidoSeleccionado != null) {
			for (Inscripcion ins : partidoSeleccionado.getEquipoB()){
				resultados.add(ins.getJugador().getPersona());
			}
		}
		return resultados;
	}

	/*public Jugador searchJugador(List<Jugador> listajugadores, String nombre) {
		return (Jugador) EntityManagerHelper
				.createQuery("from Jugador where nombre = :nombre")
				.setParameter("nombre", nombre)
				.getSingleResult();
		for (Jugador jugador : listajugadores) {
			if(jugador.getPersona().getNombre().equals(nombre)) 
				return jugador;
			}
		return null;
	}*/

}
