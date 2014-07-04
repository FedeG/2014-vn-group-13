package ar.edu.futbol5;

import ar.edu.futbol5.distribucion.CriterioDistribucion;
import ar.edu.futbol5.distribucion.DistribucionParImpar;
import ar.edu.futbol5.excepciones.BusinessException;
import ar.edu.futbol5.ordenamiento.CriterioOrdenamiento;
import ar.edu.futbol5.ordenamiento.OrdenamientoPorHandicap;

import java.util.ArrayList;
import java.util.List;

public class Partido {

	private List<Jugador> inscriptos;
	private List<Jugador> equipo1;
	private List<Jugador> equipo2;
	private EstadoPartido estado;
	private CriterioOrdenamiento criterioOrdenamiento;
	private CriterioDistribucion distribucionEquipos;

	public enum EstadoPartido {
		Abierto, EquiposGenerados, Cerrado
	}

	public Partido() {
		inscriptos = new ArrayList<Jugador>();
		estado = EstadoPartido.Abierto;
		distribucionEquipos = new DistribucionParImpar();
		criterioOrdenamiento = new OrdenamientoPorHandicap();
	}

	public void generarEquipos() {
		this.validarInscripcion();
		equipo1 = new ArrayList<Jugador>();
		equipo2 = new ArrayList<Jugador>();
		this.ordenarEquipos();
		this.distribuirEquipos();
		estado = EstadoPartido.EquiposGenerados;
	}

	private void validarInscripcion() {
		if (inscriptos.size() < 10)
			throw new BusinessException("No hay suficientes jugadores para generar los equipos.");
		if (estado == EstadoPartido.Abierto)
			throw new BusinessException("El partido aun esta abierto, no se pueden generar equipos.");
		if ( estado == EstadoPartido.EquiposGenerados)
			throw new BusinessException("Ya se han generado los equipos anteriormente.");
	}

	public List<Jugador> ordenarEquipos() {
		return criterioOrdenamiento.ordenar(this.getInscriptos());
	}

	public void distribuirEquipos(){
		this.equipo1 = distribucionEquipos.ObtenerEquipo(this.getInscriptos(), distribucionEquipos.posicionesEquipo1);
		this.equipo2 = distribucionEquipos.ObtenerEquipo(this.getInscriptos(), distribucionEquipos.posicionesEquipo2);
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
				throw new BusinessException("No hay m�s lugar");
			}
		}
	}

	private Jugador jugadorQueCedeLugar() {
		for (Jugador inscripto : inscriptos) {
			if (inscripto.dejaLugarAOtro()) {
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

	public void setCriterioOrdenamiento(
			CriterioOrdenamiento criterioOrdenamiento) {
		this.criterioOrdenamiento = criterioOrdenamiento;
	}

	public void setDistribucionEquipos(CriterioDistribucion distribucionEquipos) {
		this.distribucionEquipos = distribucionEquipos;
	}

	public List<Jugador> getEquipo1() {
		return equipo1;
	}

	public List<Jugador> getEquipo2() {
		return equipo2;
	}

}
