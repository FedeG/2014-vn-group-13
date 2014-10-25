package com.tpa.app.repo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import org.uqbar.commons.utils.Observable;
import com.tpa.app.db.EntityManagerHelper;
import com.tpa.app.model.Jugador;
import com.tpa.app.viewModel.BusquedaMultiple;

@Observable
public class RepositorioJugadores implements Serializable {
	private static final RepositorioJugadores instance = new RepositorioJugadores();

	public static RepositorioJugadores getInstance() {
		return instance;
	}
	// ********************************************************
	// ** Getter
	// ********************************************************

	@SuppressWarnings("unchecked")
	public List<Jugador> getData() {
		return EntityManagerHelper.createQuery("from Jugador").getResultList();
	}

	// ********************************************************
	// ** Altas
	// ********************************************************

	public void create(Jugador jugador) {
		EntityManagerHelper.persist(jugador);
	}

	// ********************************************************
	// ** Búsquedas
	// ********************************************************

	public List<Jugador> search(BusquedaMultiple filtros) {
		return getData().stream().filter(j -> filtros.evaluarJugador(j))
				.collect(Collectors.toList());
	}
}
