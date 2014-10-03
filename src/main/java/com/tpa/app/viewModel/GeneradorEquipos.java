package com.tpa.app.viewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.model.Administrador;
import com.tpa.app.model.ByIndex;
import com.tpa.app.model.Criterio;
import com.tpa.app.model.Divisor;
import com.tpa.app.model.GeneradorDeEquipos;
import com.tpa.app.model.Inscripcion;
import com.tpa.app.model.Partido;
import com.tpa.app.repo.RepositorioPartidos;
import com.tpa.app.view.EquiposGeneradosWindow;

@Observable
public class GeneradorEquipos implements Serializable {
	
	private Partido partidoSeleccionado;
	private String ordenamientoSeleccionado;
	private String divisionSeleccionada;
	private Administrador administrador;
	
	public GeneradorEquipos() {
		RepositorioPartidos repo = RepositorioPartidos.getInstance();
		this.administrador = repo.getAdministrador();
	}
	
	public void generar() {
		this.getPartidoSeleccionado().verificarConfirmacion();
		GeneradorDeEquipos generador = new GeneradorDeEquipos();
		List<Criterio> critOrden = new ArrayList<Criterio>();
		critOrden = crearListaSegunRadioButton();
		List<Inscripcion> inscripciones = generador.ordenarJugadores(critOrden, this.getPartidoSeleccionado());
		ByIndex divisor = crearDivisorSegunRadioButton();
		generador.dividirEquipos(divisor, this.getPartidoSeleccionado(), inscripciones);
	}
	private ByIndex crearDivisorSegunRadioButton() {
		for (Divisor divisor: this.getAdministrador().getDivisores()){
			if (this.getDivisionSeleccionada() == ((ByIndex) divisor).getNombre())
				return (ByIndex) divisor;
		}
		return null;
	}

	private List<Criterio> crearListaSegunRadioButton() {
		// TODO 
			// Es una lista para poder usar varios criterios :D
				// pero hay que cambiar el como se usa porque no se que componente de arena tengo que usar :P
		List<Criterio> criterios = new ArrayList<Criterio>();
		for (Criterio criterio: this.getAdministrador().getCriterios()){
			if (this.getOrdenamientoSeleccionado() == criterio.getNombre())
				criterios.add(criterio);
		}
		return criterios;
	}
	public Partido getPartidoSeleccionado() {
		return this.partidoSeleccionado;
	}
	
	public void setPartidoSeleccionado(Partido partidoSeleccionado) {
		this.partidoSeleccionado = partidoSeleccionado;
	}

	public String getOrdenamientoSeleccionado() {
		return ordenamientoSeleccionado;
	}

	public void setOrdenamientoSeleccionado(String ordenamientoSeleccionado) {
		this.ordenamientoSeleccionado = ordenamientoSeleccionado;
	}
	
	public List<String> getOrdenamientos(){
		return this.administrador
			.getCriterios()
			.stream()
			.map(criterio -> criterio.getNombre())
			.collect(Collectors.toList());
	}

	public String getDivisionSeleccionada() {
		return divisionSeleccionada;
	}

	public void setDivisionSeleccionada(String divisionSeleccionada) {
		this.divisionSeleccionada = divisionSeleccionada;
	}

	public List<String> getSelecciones(){
		return this.administrador
			.getDivisores()
			.stream()
			.map(divisor -> ((ByIndex) divisor).getNombre())
			.collect(Collectors.toList());
	}

	public Administrador getAdministrador() {
		return administrador;
	}

}
