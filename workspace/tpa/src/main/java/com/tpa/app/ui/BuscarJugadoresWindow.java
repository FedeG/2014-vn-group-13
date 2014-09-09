package com.tpa.app.ui;

import java.awt.Color;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;


import com.tpa.app.Jugador;
import com.tpa.app.domain.BuscadorJugadores;

public class BuscarJugadoresWindow extends  SimpleWindow<BuscadorJugadores> {

	public BuscarJugadoresWindow(WindowOwner parent) {
		super(parent, new BuscadorJugadores());
		this.getModelObject().search();
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
		.onClick(new MessageSend(this.getModelObject(), "search"))
		.setAsDefault()
		.disableOnError();		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new ColumnLayout(2));
		
		new Label(searchFormPanel).setText("Nombre").setForeground(Color.BLUE);
		//new TextBox(searchFormPanel).bindValueToProperty(modelProperty);
		// este text box deberia bidear contra el transformer a nombre
		//new TextBox(searchFormPanel).bind (???);
		
		new Label(searchFormPanel).setText("Apodo").setForeground(Color.BLUE);
		// pasa lo mismo con el transformer aca

		
	}
	
	protected void createResultsGrid(Panel mainPanel) {
		
		Table<Jugador> table = new Table<Jugador>(mainPanel, Jugador.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("resultados");
		table.bindValueToProperty("jugadorSeleccionado");

		this.describeResultsGrid(table);
	}
	
	protected void createGridActions(Panel mainPanel) {
		
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		Button remove = new Button(actionsPanel);
		remove.setCaption("Ver Jugador Seleccionado");
		remove.onClick(new MessageSend(this, "verJugadorSeleccionado"));

		// Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
		NotNullObservable elementSelected = new NotNullObservable("celularSeleccionado");
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
		
		Column<Jugador> ingresoColumn = new Column<Jugador>(table);
		ingresoColumn.setTitle("Nombre");
		ingresoColumn.setFixedSize(100);
		ingresoColumn.bindContentsToTransformer(new NombreTransformer());
		
		Column<Jugador> ingresoColumn2 = new Column<Jugador>(table);
		ingresoColumn2.setTitle("Apodo");
		ingresoColumn2.setFixedSize(100);
		ingresoColumn2.bindContentsToTransformer(new ApodoTransformer());
		
		// para poner el handicap hay que hacer que sea atributo de jugador y hoy no esta asi, primero hay que modificar eso
		
		// para poner promedio pasa lo mismo salvo que usemos un trasnformer que tome al jugador y devuelva un int como promedio, pero me paece medio choto
		
		Column<Jugador> ingresoColumn3 = new Column<Jugador>(table);
		ingresoColumn3.setTitle("Promedio");
		ingresoColumn3.setFixedSize(100);
		ingresoColumn3.bindContentsToTransformer(new PromedioTransformer());
		
	
	}
	
}
