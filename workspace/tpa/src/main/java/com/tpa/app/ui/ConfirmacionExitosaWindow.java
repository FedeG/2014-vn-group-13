package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import com.tpa.app.domain.PantallaPrincipal;

public class ConfirmacionExitosaWindow extends  SimpleWindow<PantallaPrincipal> {

	public ConfirmacionExitosaWindow(WindowOwner parent) {
		super(parent, new PantallaPrincipal());

	}

	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		
		this.setTitle("");
		super.createMainTemplate(mainPanel);

	}

	

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);		
	}


	@Override
	protected void addActions(Panel arg0) {
		// TODO Auto-generated method stub
		
	}
}
