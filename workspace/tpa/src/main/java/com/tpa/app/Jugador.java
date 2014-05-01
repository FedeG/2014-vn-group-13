package com.tpa.app;

public class Jugador {
	private int partidosJugados;
	private int inasistencias;
	private int edad;
	private TipoJugador tipoJugador;

	public Jugador(int edad, TipoJugador tipo) {
		this.setEdad(edad);
		this.partidosJugados = 0;
		this.inasistencias = 0;
		this.setTipoJugador(tipo);
	}

	public int getInasistencias() {
		return this.inasistencias;
	}
	
	public void SumarInasistencia() {
		this.inasistencias += 1;
	}
	public void SumarPartidoJugado() {
		this.partidosJugados += 1;
	}
	
	public int getPartidosJugados() {
		return this.partidosJugados;
	}
	
	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public TipoJugador getTipoJugador() {
		return tipoJugador;
	}

	public void setTipoJugador(TipoJugador tipoJugador) {
		this.tipoJugador = tipoJugador;
	}
	
	public void InscribirseA(Partido partido) {
		partido.inscribirJugador(this);
	}

	public int getPrioridad() {
		// TODO Auto-generated method stub
		return 100;
	}
}
