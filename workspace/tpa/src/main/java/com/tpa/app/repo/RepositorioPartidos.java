package com.tpa.app.repo;

import static org.mockito.Mockito.mock;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import com.tpa.app.MailSender;
import com.tpa.app.Partido;
import com.tpa.app.PartidoMailSender;

@Observable
public class RepositorioPartidos implements Serializable {
	
	private static RepositorioPartidos instance;
	private List<Partido> data = new ArrayList<Partido>();

	public static synchronized RepositorioPartidos getInstance() {
		if (instance == null) {
			instance = new RepositorioPartidos();
		}
		return instance;
	}

	private RepositorioPartidos() {

		LocalDateTime fecha_y_hora = LocalDateTime.now();
		
		this.create(new Partido(fecha_y_hora, "Parque Patricios", 10));
		this.create(new Partido(fecha_y_hora, "Adrogue", 10));
		this.create(new Partido(fecha_y_hora, "Lugano", 10));
		
	}

	// ********************************************************
	// ** Altas y bajas
	// ********************************************************

	public void create(Partido partido) {
		this.data.add(partido);
	}

	// ********************************************************
	// ** Búsquedas
	// ********************************************************


	public List<Partido> search() {
		List<Partido> resultados = new ArrayList<Partido>();

		for (Partido partido : this.data) {
			
				resultados.add(partido);
			}
		return resultados;
		
	}



}
