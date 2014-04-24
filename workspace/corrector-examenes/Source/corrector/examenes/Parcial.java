package corrector.examenes;
import java.util.*;


public class Parcial {
	public Collection<Item> items;
	public String alumno;
	
	public int puntajeAlumno(){
		return this.puntajeByCondition(Item::esCorrecto)
	}
	
	public int puntajeTotal(){
		return this.puntajeByCondition(item -> item)
	}
	
	public int puntajeByCondition(Predicate<Item> condition){
	    int total = items.stream()
	            .filter(item -> condition(item))
				.mapToInt(item -> item.pregunta.pesoEspecifico)
				.sum();

		return total;
	} 
}

