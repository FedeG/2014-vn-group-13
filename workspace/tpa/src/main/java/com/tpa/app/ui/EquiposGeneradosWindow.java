package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import com.tpa.app.Jugador;
import com.tpa.app.Partido;
import com.tpa.app.Persona;
import com.tpa.app.domain.PantallaPrincipal;

public class EquiposGeneradosWindow extends  SimpleWindow<Partido> {

	public EquiposGeneradosWindow(WindowOwner parent) {
		super(parent, null);

	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		
		this.setTitle("Ver Equipos Generados");
		super.createMainTemplate(mainPanel);
		
		this.createResultsGrid(mainPanel);
		this.createGridActions(mainPanel);

	}

	protected void createResultsGrid(Panel mainPanel) {
		
		Table<Persona> table = new Table<Persona>(mainPanel, Persona.class);
		table.setHeigth(200);
		table.setWidth(450);

		//table.bindItemsToProperty("resultadosJugadoresA");
		//table.bindValueToProperty("jugadorSeleccionado");

		this.describeResultsGrid(table);
	}
	
	protected void describeResultsGrid(Table<Persona> table) {
		
		new Column<Persona>(table) //
			.setTitle("Nombre")
			.setFixedSize(250)
			.bindContentsToProperty("nombre");
	
	}
	
	protected void createGridActions(Panel mainPanel) {
		
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		Button edit = new Button(actionsPanel);
		edit.setCaption("Ver");
		edit.onClick(new MessageSend(this, "ver"));

		// Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
		NotNullObservable elementSelected = new NotNullObservable("partidoSeleccionado");
		edit.bindEnabled(elementSelected);
	}
	

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
		.setCaption("Confirmar")
		.onClick(new MessageSend(this, "confirmar"));
		
	}
	
	public void confirmar() {
		this.openDialog(new ConfirmacionExitosaWindow(this));
	}
	

	private void openDialog(ConfirmacionExitosaWindow confirmacionExitosaWindow) {
		confirmacionExitosaWindow.open();
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);		
	}
}
