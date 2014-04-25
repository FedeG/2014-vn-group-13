package com.corrector.app;
import java.util.*;


public class Parcial {
	public Collection<Item> items;
	public String alumno;
	
	public int puntajeAlumno(){

		int total = items.stream()
				.filter(Item::esCorrecto)
				.mapToInt(item -> item.pregunta.pesoEspecifico)
				.sum();
		
		return total;
	}
	
	public int puntajeTotal(){
		
		int total = items.stream()
				.mapToInt(item -> item.pregunta.pesoEspecifico)
				.sum();

		return total;
		
	}
	
}

