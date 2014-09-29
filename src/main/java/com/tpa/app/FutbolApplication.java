package com.tpa.app;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import com.tpa.app.view.PantallaPrincipalWindow;

/**
 * Correr esta clase con el siguiente argument
 * 
 * -Djava.system.class.loader=com.uqbar.apo.APOClassLoader
 */
public class FutbolApplication extends Application {

	public static void main(String[] args) {
		new FutbolApplication().start();
	}

	@Override
	protected Window<?> createMainWindow() {
		return new PantallaPrincipalWindow(this);
	}
	
}
