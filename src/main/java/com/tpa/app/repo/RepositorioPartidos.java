package com.tpa.app.repo;

//import static org.mockito.Mockito.mock;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.db.EntityManagerHelper;
import com.tpa.app.model.Administrador;
import com.tpa.app.model.ByIndex;
import com.tpa.app.model.Inscripcion;
import com.tpa.app.model.Jugador;
import com.tpa.app.model.Partido;
import com.tpa.app.model.PartidoMailSender;
import com.tpa.app.model.Persona;
import com.tpa.app.model.PorHandicap;
import com.tpa.app.model.PorPromedio;

@Observable
public class RepositorioPartidos implements Serializable {

	//@Mock
	//MailSender mailSenderMock;
	
	private Administrador administrador;
	private static final RepositorioPartidos instance = new RepositorioPartidos();
	public static RepositorioPartidos getInstance() {
	    //if (instance == null) instance = new RepositorioPartidos();
		return instance;
	}

	public RepositorioPartidos() {
		Timestamp fecha_y_hora = Timestamp.from(Instant.now());
		this.administrador = new Administrador(new Persona(fecha_y_hora, "admin_lomas@futbol.com", "admin"), new PartidoMailSender());

	}
	
	// ********************************************************
	// ** Getter
	// ********************************************************
		
	public List<Partido> getData(){
		return EntityManagerHelper.createQuery(String.format("from Partido where administrador.id = 1")).getResultList();
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
}
