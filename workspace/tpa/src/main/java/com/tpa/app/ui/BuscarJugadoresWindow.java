package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;

import com.tpa.app.Jugador;
import com.tpa.app.domain.BuscadorJugadores;

public class BuscarJugadoresWindow extends  SimpleWindow<BuscadorJugadores> {

	public BuscarJugadoresWindow(WindowOwner parent) {
		super(parent, new BuscadorJugadores());

	}

	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		
		this.setTitle("Buscar Jugadores");
		super.createMainTemplate(mainPanel);
		this.createResultsGrid(mainPanel);
		this.createGridActions(mainPanel);

	}


	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
		.setCaption("Buscar")
		.onClick(new MessageSend(this.getModelObject(), "buscar"));
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {		
	}
	
	protected void createResultsGrid(Panel mainPanel) {
		Table<Jugador> table = new Table<Jugador>(mainPanel, Jugador.class);
		table.setHeigth(200);
		table.setWidth(450);

		//table.bindItemsToProperty("resultados");
		//table.bindValueToProperty("celularSeleccionado");

		this.describeResultsGrid(table);
	}
	
	protected void createGridActions(Panel mainPanel) {
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		Button remove = new Button(actionsPanel);
		remove.setCaption("Ver Jugador Seleccionado");
		remove.onClick(new MessageSend(this, "verJugadorSeleccionado"));

		// Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
		//NotNullObservable elementSelected = new NotNullObservable("celularSeleccionado");
		//remove.bindEnabled(elementSelected);
		//edit.bindEnabled(elementSelected);
	}
	
	public void verJugadorSeleccionado() {
		this.openDialog(new VerJugadorSeleccionadoWindow(this, this.getModelObject().getJugadorSeleccionado()));
	}
	
	private void openDialog(VerJugadorSeleccionadoWindow verJugadorSeleccionadoWindow) {
		verJugadorSeleccionadoWindow.open();
	}

	protected void describeResultsGrid(Table<Jugador> table) {
		new Column<Jugador>(table) //
			.setTitle("Nombre")
			.setFixedSize(150);
		//	.bindContentsToProperty("nombre");

		new Column<Jugador>(table) //
			.setTitle("Número")
			.setFixedSize(100);
		//	.bindContentsToProperty("numero");

		Column<Jugador> modeloColumn = new Column<Jugador>(table);
		modeloColumn.setTitle("Modelo");
		modeloColumn.setFixedSize(150);
		//modeloColumn.bindContentsToProperty("modeloCelular");

		Column<Jugador> ingresoColumn = new Column<Jugador>(table);
		ingresoColumn.setTitle("Recibe resumen de cuenta");
		ingresoColumn.setFixedSize(50);
		//ingresoColumn.bindContentsToTransformer(new BooleanToSiNoTransformer());
	}
	
}
