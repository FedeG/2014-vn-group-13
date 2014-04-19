package corrector.examenes;
import java.util.*;


public class Parcial {
	public Collection<Item> items;
	public String alumno;
	
	public int puntajeAlumno(){
		int total = 0;
		
		for (Item item : items) {
			if (item.esCorrecto()) {
				total = total + item.pregunta.pesoEspecifico;
			}
		}
		
		return total;
	}
	
	public int puntajeTotal(){
		int total = 0;
		
		for (Item item : items) {
			
				total = total + item.pregunta.pesoEspecifico;
			
		}
		
		return total;
	}
	
}

