package com.tpa.app.ui;


import java.util.Arrays;
import java.awt.Color;
import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.RadioSelector;
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
		
		/* Desde ya esto es un code smell Long Method, pero hay que admitir
		 * que hace a la instanciacion de la ventana mucho mas entendible y amigable
		 */
		
		this.setTitle("Buscar Jugadores");
		mainPanel.setLayout(new VerticalLayout());
		super.createMainTemplate(mainPanel);
		
		/* Controles de formulario de busqueda */
		
		Panel opcionesDeBusqueda = new Panel(mainPanel); 
		opcionesDeBusqueda.setLayout(new ColumnLayout(4));
		
		new Label(opcionesDeBusqueda).setText("Comienza con:");
		new TextBox(opcionesDeBusqueda).setWidth(80);
		new Label(opcionesDeBusqueda).setText("Contiene:");
		new TextBox(opcionesDeBusqueda).setWidth(80);
		
		new Label(opcionesDeBusqueda).setText("Handicap desde:");
		new TextBox(opcionesDeBusqueda).setWidth(80);
		new Label(opcionesDeBusqueda).setText("Handicap hasta:");
		new TextBox(opcionesDeBusqueda).setWidth(80);
		
		new Label(opcionesDeBusqueda).setText("Promedio desde:");
		new TextBox(opcionesDeBusqueda).setWidth(80);
		new Label(opcionesDeBusqueda).setText("Promedio hasta:");
		new TextBox(opcionesDeBusqueda).setWidth(80);
		
		new Label(opcionesDeBusqueda).setText("Tuvo infraccion:");
		new RadioSelector<String>(opcionesDeBusqueda).setContents(Arrays.asList("Si","No"), "infraccion");
		new Label(opcionesDeBusqueda).setText("Anterior a:");
		new TextBox(opcionesDeBusqueda).setWidth(80);
		
		new Button(mainPanel)
		.setCaption("Buscar")
		.onClick(new MessageSend(this.getModelObject(), "search"));
		
		/* Create Grid */
		
		Table<Jugador> table = new Table<Jugador>(mainPanel, Jugador.class);
		table.setHeigth(200);
		table.setWidth(600);
		table.bindItemsToProperty("resultados");
		//table.bindValueToProperty("celularSeleccionado");
		
		/* Grid Description */
		
		new Column<Jugador>(table) //
		.setTitle("Nombre")
		.setFixedSize(225)
		.bindContentsToTransformer(new NombreTransformer());

		new Column<Jugador>(table) //
		.setTitle("Apodo")
		.setFixedSize(225)
		.bindContentsToTransformer(new ApodoTransformer());

		Column<Jugador> modeloColumn = new Column<Jugador>(table);
		modeloColumn.setTitle("Handicap");
		modeloColumn.setFixedSize(75);
		modeloColumn.bindContentsToProperty("handicap");
	
		Column<Jugador> ingresoColumn = new Column<Jugador>(table);
		ingresoColumn.setTitle("Promedio");
		ingresoColumn.setFixedSize(75);
		ingresoColumn.bindContentsToTransformer(new PromedioTransformer());
		
		/* Grid Actions */
		
		Panel botonera = new Panel(mainPanel);
		botonera.setLayout(new HorizontalLayout());
		
		Button verJugador = new Button(botonera);
		verJugador.setCaption("Ver Jugador Seleccionado");
		verJugador.setWidth(300);
		verJugador.onClick(new MessageSend(this, "verJugadorSeleccionado"));
		
		Button volver = new Button(botonera);
		volver.setCaption("Volver");
		volver.setWidth(300);
		volver.onClick(new MessageSend(this, "volverAtras"));

		//Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
		//NotNullObservable elementSelected = new NotNullObservable("verJugadorSeleccionado");
		//verJugador.bindEnabled(elementSelected);
	}

	@Override
	protected void addActions(Panel actionsPanel) {
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {		
	}
		
	public void verJugadorSeleccionado() {
		this.openDialog(new VerJugadorSeleccionadoWindow(this, this.getModelObject().getJugadorSeleccionado()));
	}
	
	private void openDialog(VerJugadorSeleccionadoWindow nuevaVentana) {
		nuevaVentana.open();
	}
	
	public void volverAtras(){
		this.close();
	}
}
