package com.tpa.app.ui;

import com.tpa.app.Jugador;
import com.uqbar.commons.collections.Transformer;

/**
 * Transforma un booleano a un String, devolviendo "SÍ" en caso de ser verdadero y "NO" en caso de ser falso.
 * 
 * @author npasserini
 */
public final class ApodoTransformer implements Transformer<Jugador, String> {
	@Override
	public String transform(Jugador jugador) {
		return jugador.getPersona().getApodo();
	}
}