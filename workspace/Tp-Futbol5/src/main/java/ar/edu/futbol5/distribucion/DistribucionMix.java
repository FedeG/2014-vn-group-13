package ar.edu.futbol5.distribucion;

import java.util.ArrayList;

public class DistribucionMix extends CriterioDistribucion{
	
	public DistribucionMix() {
		this.posicionesEquipo1 = new ArrayList<Integer>();
		this.posicionesEquipo2 = new ArrayList<Integer>();
		posicionesEquipo1.add(0);
		posicionesEquipo1.add(3);
		posicionesEquipo1.add(4);
		posicionesEquipo1.add(7);
		posicionesEquipo1.add(8);
		posicionesEquipo2.add(1);
		posicionesEquipo2.add(2);
		posicionesEquipo2.add(5);
		posicionesEquipo2.add(6);
		posicionesEquipo2.add(9);
	}
}
