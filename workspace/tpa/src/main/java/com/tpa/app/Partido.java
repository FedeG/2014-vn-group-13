package com.tpa.app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection; //Buscar de SortedSet o SortedMap


public class Partido {

	private String lugar;
	private LocalDateTime fechaHora;
	private int cupo;
	private ArrayList<Jugador> jugadoresInscriptos;

	public static Partido CrearPartidoFutbol5(LocalDateTime fecha_y_hora, String lugar){
		return new Partido(fecha_y_hora, lugar, 10);
	}
	
	public Partido(LocalDateTime fecha_y_hora, String lugar, int cupo) {
		this.setFechaHora(fecha_y_hora);
		this.setLugar(lugar);
		this.setCupo(cupo);
		this.jugadoresInscriptos = new ArrayList<Jugador>();
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public void inscribirJugador(Jugador jugador) {
		if (getJugadoresInscriptos().contains(jugador))
			throw new YaEstaInscriptoAlPartidoExcepcion();
			
		
		if (getCantidadJugadoresInscriptos() >= getCupo())
			throw new CupoMaximoSuperadoExcepcion();
		
		
		this.getJugadoresInscriptos().add(jugador);
	}

	public Collection<Jugador> getJugadoresInscriptos() {
		return jugadoresInscriptos;
	}
	public int getCantidadJugadoresInscriptos() {
		return getJugadoresInscriptos().size();
	}
	public int getCantidadJugadoresInscriptosPorTipo(Class clase ) {
		return (int) getJugadoresInscriptos()
				.stream()
				.filter(j -> j.getTipoJugador().getClass() == clase )
				.count();
	}
}