package com.tpa.app;

public class PorCalificacion implements Criterio {
	private String nombre = "Criterio Por Calificacion";
	private int cantidadDeCalificaciones;
	
	public PorCalificacion(int cantidadDeCalificaciones) {
		this.setCantidadDeCalificaciones(cantidadDeCalificaciones);
	}
	
	public PorCalificacion(String nombre, int cantidadDeCalificaciones) {
		this.setNombre(nombre);
		this.setCantidadDeCalificaciones(cantidadDeCalificaciones);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidadDeCalificaciones() {
		return cantidadDeCalificaciones;
	}

	public void setCantidadDeCalificaciones(int cantidadDeCalificaciones) {
		this.cantidadDeCalificaciones = cantidadDeCalificaciones;
	}
	
	@Override
	public int dameTuValor(Inscripcion inscripcion) {
		//magia faltante por sueño
		return 0;
	}

}
