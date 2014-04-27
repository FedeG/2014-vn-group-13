package com.corrector.app;
import java.util.Collection;
import java.util.function.Predicate;


public class Parcial {
	public Collection<Item> items;
	public String alumno;
	
	public int puntajeAlumno(){
		return this.puntajeByCondition( Item::esCorrecto);
	}
	
	public int puntajeTotal(){
		return this.puntajeByCondition(item -> true);
	}
	
	public int puntajeByCondition(Predicate<Item> condition){
	    int total = items.stream()
	            .filter(condition)
				.mapToInt(item -> item.pregunta.pesoEspecifico)
				.sum();

		return total;
	} 
}

