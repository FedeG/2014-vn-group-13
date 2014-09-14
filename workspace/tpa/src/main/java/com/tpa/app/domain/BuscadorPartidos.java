package com.tpa.app.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.uqbar.commons.utils.Observable;
import com.tpa.app.Partido;
import com.tpa.app.repo.RepositorioPartidos;

@Observable
public class BuscadorPartidos implements Serializable {
	
	private Partido partidoSeleccionado;
	private List<Partido> resultados;
	private LocalDateTime fechaHora;
	private String lugar;
	public List<String> ordenamientos;
	private String ordenamientoSeleccionado;
	public List<String> selecciones;
	private String seleccionSeleccionada;
	
	
	public void search() {
		this.setResultados(RepositorioPartidos.getInstance().search());
	}

	
	public Partido getPartidoSeleccionado() {
		return this.partidoSeleccionado;
	}
	
	public void setPartidoSeleccionado(Partido partidoSeleccionado) {
		this.partidoSeleccionado = partidoSeleccionado;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public List<Partido> getResultados() {
		return resultados;
	}

	public void setResultados(List<Partido> resultados) {
		this.resultados = resultados;
	}

	public String getOrdenamientoSeleccionado() {
		return ordenamientoSeleccionado;
	}

	public void setOrdenamientoSeleccionado(String ordenamientoSeleccionado) {
		this.ordenamientoSeleccionado = ordenamientoSeleccionado;
	}
	
	public List<String> getOrdenamientos(){
		return Arrays.asList("Por Promedio", "Por Handicap", "Mixto");
	}

	public String getSeleccionSeleccionada() {
		return seleccionSeleccionada;
	}

	public void setSeleccionSeleccionada(String seleccionSeleccionada) {
		this.seleccionSeleccionada = seleccionSeleccionada;
	}
	
	public List<String> getSelecciones(){
		return Arrays.asList("Par/Impar", "1,4,5,8,9");
	}
}
