package com.tpa.app.viewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;
import com.tpa.app.model.Administrador;
import com.tpa.app.model.Criterio;
import com.tpa.app.model.Divisor;
import com.tpa.app.model.GeneradorDeEquipos;
import com.tpa.app.model.Inscripcion;
import com.tpa.app.model.Partido;
import com.tpa.app.repo.RepositorioPartidos;

@Observable
public class GeneradorEquipos implements Serializable {

	private Partido partidoSeleccionado;
	private Criterio ordenamientoSeleccionado;
	private Divisor divisionSeleccionada;
	private Administrador administrador;

	public GeneradorEquipos() {
		RepositorioPartidos repo = RepositorioPartidos.getInstance();
		this.administrador = repo.getAdministrador();
	}

	public void generar() {
		if(getOrdenamientoSeleccionado() == null)
			throw new UserException("Debe seleccionar un criterio de ordenamiento");
		if(getDivisionSeleccionada() == null)
			throw new UserException("Debe seleccionar un criterio de división");
		this.getPartidoSeleccionado().verificarConfirmacion();
		GeneradorDeEquipos generador = new GeneradorDeEquipos();
		List<Criterio> critOrden = new ArrayList<Criterio>();
		critOrden = crearListaSegunRadioButton();
		List<Inscripcion> inscripciones = generador.ordenarJugadores(critOrden,
				this.getPartidoSeleccionado());
		// ByIndex divisor = crearDivisorSegunRadioButton();
		generador.dividirEquipos(getDivisionSeleccionada(),
				this.getPartidoSeleccionado(), inscripciones);
	}

	// private ByIndex crearDivisorSegunRadioButton() {
	// for (Divisor divisor : this.getAdministrador().getDivisores()) {
	// if (this.getDivisionSeleccionada().getNombre() == divisor.getNombre())
	// return (ByIndex) divisor;
	// }
	// }

	private List<Criterio> crearListaSegunRadioButton() {
		// TODO
		// Es una lista para poder usar varios criterios :D
		// pero hay que cambiar el como se usa porque no se que componente de
		// arena tengo que usar :P
		List<Criterio> criterios = new ArrayList<Criterio>();
		criterios.add(getOrdenamientoSeleccionado());
		//for (Criterio criterio : this.getAdministrador().getCriterios()) {
		//	if (this.getOrdenamientoSeleccionado().getNombre() == criterio
		//			.getNombre())
		//		criterios.add(criterio);
		//}
		return criterios;
	}

	public Partido getPartidoSeleccionado() {
		return this.partidoSeleccionado;
	}

	public void setPartidoSeleccionado(Partido partidoSeleccionado) {
		this.partidoSeleccionado = partidoSeleccionado;
	}

	public Criterio getOrdenamientoSeleccionado() {
		return ordenamientoSeleccionado;
	}

	public void setOrdenamientoSeleccionado(Criterio ordenamientoSeleccionado) {
		this.ordenamientoSeleccionado = ordenamientoSeleccionado;
	}

	public List<Criterio> getOrdenamientos() {
		return this.administrador.getCriterios().stream()
		// .map(criterio -> criterio.getNombre())
				.collect(Collectors.toList());
	}

	public Divisor getDivisionSeleccionada() {
		return divisionSeleccionada;
	}

	public void setDivisionSeleccionada(Divisor divisionSeleccionada) {
		this.divisionSeleccionada = divisionSeleccionada;
	}

	public List<Divisor> getDivisores() {
		return this.administrador.getDivisores().stream()
				//.map(divisor -> ((ByIndex) divisor).getNombre())
				.collect(Collectors.toList());
	}

	public Administrador getAdministrador() {
		return administrador;
	}

}
