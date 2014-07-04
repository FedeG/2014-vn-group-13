package ar.edu.futbol5;

import ar.edu.futbol5.excepciones.BusinessException;
import ar.edu.futbol5.ordenamiento.CriterioOrdenamiento;
import ar.edu.futbol5.ordenamiento.OrdenamientoPorHandicap;
import ar.edu.futbol5.utilitarios.Lists;

import java.util.ArrayList;
import java.util.List;

public class Partido {

	private List<Jugador> inscriptos;
	private Equipo equipo1;
	private Equipo equipo2;
	private EstadoPartido estado;
	private CriterioOrdenamiento criterioOrdenamiento;
	private int distribucionEquipos; // 5 es par/impar, 16 = 1,4,5,8,9 vs. 2,3,6,7,10
	
	public enum EstadoPartido {
		Abierto,
		EquiposGenerados,
		Cerrado
	}
	
	public Partido() {
		inscriptos = new ArrayList<Jugador>();
		estado = EstadoPartido.Abierto;
		distribucionEquipos = 5; // par/impar
		criterioOrdenamiento = new OrdenamientoPorHandicap();
	}

	public void generarEquipos() {
		
		if (this.validarInscripcion() == -1) {
			throw new BusinessException("Hubo un error");
		}
		
		equipo1 = new Equipo();
		equipo2 = new Equipo();
		List<Integer> posiciones1 = new ArrayList<Integer>();
		List<Integer> posiciones2 = new ArrayList<Integer>();
		
		if (distribucionEquipos == 5) {			
			posiciones1.add(0);posiciones1.add(2);posiciones1.add(4);posiciones1.add(6);posiciones1.add(8);
			posiciones2.add(1);posiciones2.add(3);posiciones2.add(5);posiciones2.add(7);posiciones2.add(9);
		} else {
			// distribucionEquipos == 16 que ordena de esta manera
			posiciones1.add(0);posiciones1.add(3);posiciones1.add(4);posiciones1.add(7);posiciones1.add(8);
			posiciones2.add(1);posiciones2.add(2);posiciones2.add(5);posiciones2.add(6);posiciones2.add(9);
		}		
		
		equipo1.distribuir(this.ordenarEquipos(), posiciones1);
		equipo2.distribuir(this.ordenarEquipos(), posiciones2);
		
		estado = EstadoPartido.EquiposGenerados;
	}

	private int validarInscripcion() {
		if ( noCumpleInscripcion()) {
			return -1;
		} else
		return 0;
	}
	
	public boolean noCumpleInscripcion() {
		return (inscriptos.size() < 10 || estado == EstadoPartido.Abierto || estado == EstadoPartido.EquiposGenerados  );
	}

	public List<Jugador> ordenarEquipos() {
		return criterioOrdenamiento.ordenar(this);
	}

	void inscribir(Jugador jugador) {
		if (inscriptos.size() < 10) {
			this.inscriptos.add(jugador);
		} else {
			Jugador jugadorQueCedeLugar = jugadorQueCedeLugar();
			if (jugadorQueCedeLugar != null) {
				this.inscriptos.remove(jugadorQueCedeLugar);
				this.inscriptos.add(jugador);
			} else {
				throw new BusinessException("No hay más lugar");
			}
		}
	}
	private Jugador jugadorQueCedeLugar() {
		for (Jugador inscripto : inscriptos) {
			if(inscripto.dejaLugarAOtro()){
				return inscripto;
			}
		}
		return null;
	}

	public void cerrar() {
		estado = EstadoPartido.Cerrado;
	}

	public List<Jugador> getInscriptos() {
		return inscriptos;
	}

	public void setCriterioOrdenamiento(CriterioOrdenamiento criterioOrdenamiento) {
		this.criterioOrdenamiento = criterioOrdenamiento;
	}

	public void setDistribucionEquipos(int distribucionEquipos) {
		this.distribucionEquipos = distribucionEquipos;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}
	
}
