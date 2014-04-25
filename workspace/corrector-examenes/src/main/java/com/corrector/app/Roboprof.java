package main.java.com.corrector.app;


public class Roboprof {
	
	int [] [] notas = {{1,0},{2,1},{3,1},{4,1},{5,2},{6,2},{7,2},{8,3},{9,4},{10,4},{11,4},{12,5},{13,5},{14,5},{15,5},{16,6},{17,7},{18,8},{19,9},{10,10}};
			
	public int corregirConReglaDeTres(Parcial unParcial){
		return (unParcial.puntajeAlumno() * 10) / unParcial.puntajeTotal();
	}
	
	public int corregirConResta(Parcial unParcial, int n){
		return unParcial.puntajeAlumno() - n;
	}
	
	public int corregirPorTabla(Parcial unParcial){
		int i;
		for (i=0;i<20;i++){
			if (notas [i] [0] == unParcial.puntajeAlumno())
			{
				return notas [i] [1]; 
			}
		}
		return 0;
	}
	
	public int corregirTomandoNotaMasAlta(Parcial unParcial) {
		return Math.max(Math.max(corregirConReglaDeTres(unParcial), corregirConResta(unParcial,6)), corregirPorTabla(unParcial));
	}
	
	public int corregirTomandoPromedio(Parcial unParcial){
		return ((corregirConReglaDeTres(unParcial)+ corregirConResta(unParcial,6) + corregirPorTabla(unParcial))/3);
	}
	
}


