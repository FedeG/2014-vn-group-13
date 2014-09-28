package com.tpa.app.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.Administrador;
import com.tpa.app.ByIndex;
import com.tpa.app.Partido;
import com.tpa.app.repo.RepositorioPartidos;

@Observable
public class BuscadorPartidos implements Serializable {
	
	private Partido partidoSeleccionado;
	private String ordenamientoSeleccionado;
	private String seleccionSeleccionada;
	private Administrador administrador;
	
	public BuscadorPartidos() {
		RepositorioPartidos repo = RepositorioPartidos.getInstance();
		this.administrador = repo.getAdministrador();
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

	public String getSeleccionSeleccionada() {
		return seleccionSeleccionada;
	}

	public void setSeleccionSeleccionada(String seleccionSeleccionada) {
		this.seleccionSeleccionada = seleccionSeleccionada;
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
