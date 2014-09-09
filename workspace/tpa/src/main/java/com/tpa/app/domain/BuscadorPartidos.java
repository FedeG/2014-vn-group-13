package com.tpa.app.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.tpa.app.Jugador;
import com.tpa.app.Partido;
import com.tpa.app.Persona;
import com.tpa.app.repo.RepositorioPartidos;


@Observable
public class BuscadorPartidos implements Serializable {
	
	private Partido partidoSeleccionado;
	private Jugador jugadorSeleccionado;
	private List<Partido> resultados;
	private List<Persona> resultadosJugadoresA;
	private List<Persona> resultadosJugadoresB;
	private LocalDateTime fechaHora;
	private String lugar;
	private String nombre;
	public List<String> ordenamientos;
	private String ordenamientoSeleccionado;
	public List<String> selecciones;
	private String seleccionSeleccionada;
	
	
	public void search() {
		this.setResultados(RepositorioPartidos.getInstance().search());
		this.setResultadosJugadoresA(RepositorioPartidos.getInstance().searchJugadoresEquipoA(partidoSeleccionado));
		this.setResultadosJugadoresB(RepositorioPartidos.getInstance().searchJugadoresEquipoB(partidoSeleccionado));
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
	
	public List<String> getOrdenamientos()
	{
	 return Arrays.asList("Por Promedio", "Por Handicap", "Mixto");
	}

	public String getSeleccionSeleccionada() {
		return seleccionSeleccionada;
	}

	public void setSeleccionSeleccionada(String seleccionSeleccionada) {
		this.seleccionSeleccionada = seleccionSeleccionada;
	}
	
	public List<String> getSelecciones()
	{
	 return Arrays.asList("Par/Impar", "1,4,5,8,9");
	}

	public List<Persona> getResultadosJugadoresA() {
		return resultadosJugadoresA;
	}

	public void setResultadosJugadoresA(List<Persona> list) {
		this.resultadosJugadoresA = list;
	}


	public List<Persona> getResultadosJugadoresB() {
		return resultadosJugadoresB;
	}


	public void setResultadosJugadoresB(List<Persona> resultadosJugadoresB) {
		this.resultadosJugadoresB = resultadosJugadoresB;
	}


	public Jugador getJugadorSeleccionado() {
		return jugadorSeleccionado;
	}


	public void setJugadorSeleccionado(Jugador jugadorSeleccionado) {
		this.jugadorSeleccionado = jugadorSeleccionado;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




}
