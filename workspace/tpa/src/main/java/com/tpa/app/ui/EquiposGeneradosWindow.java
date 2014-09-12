package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Container;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Widget;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.model.IModel;

import com.tpa.app.Inscripcion;
import com.tpa.app.Jugador;
import com.tpa.app.Partido;
import com.tpa.app.Jugador;
import com.tpa.app.domain.PantallaPrincipal;
import com.tpa.app.domain.SelectorJugadores;

public class EquiposGeneradosWindow extends SimpleWindow<SelectorJugadores> {

	public EquiposGeneradosWindow(WindowOwner parent, SelectorJugadores partido) {
		super(parent, partido);
	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		
		this.setTitle("Ver Equipos Generados");
		this.setTaskDescription("Elija una operación a realizar");
		super.createMainTemplate(mainPanel);
		
		Panel horizontal_panel = new Panel(mainPanel);
		horizontal_panel.setLayout(new HorizontalLayout());
		this.createResultsGrid(horizontal_panel, "equipoA", "Equipo 1");
		this.createResultsGrid(horizontal_panel, "equipoB", "Equipo 2");
		this.createGridActions(mainPanel);

	}
	
	protected void createResultsGrid(Panel mainPanel, String property, String name) {

		Table<Jugador> table = new Table<Jugador>(mainPanel, Jugador.class);
		table.setHeigth(200);
		table.setWidth(250);

		table.bindItemsToProperty(property);
		table.bindValueToProperty("jugadorSeleccionado");

		this.describeResultsGrid(table, name);
	}

	protected void describeResultsGrid(Table<Jugador> table, String nombre) {
		
		new Column<Jugador>(table)
			.setTitle(nombre)
			.setFixedSize(250)
			.bindContentsToTransformer(new NombreTransformer());

	}
	
	protected void createGridActions(Panel mainPanel) {
		
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		Button edit = new Button(actionsPanel);
		edit.setCaption("Ver");
		edit.onClick(new MessageSend(this, "ver"));

		NotNullObservable elementSelected = new NotNullObservable("jugadorSeleccionado");
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
