package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.arena.widgets.RadioSelector;

import com.tpa.app.Partido;
import com.tpa.app.domain.BuscadorPartidos;


public class GenerarEquiposWindow extends  SimpleWindow<BuscadorPartidos> {

	public GenerarEquiposWindow(WindowOwner parent) {
		super(parent, new BuscadorPartidos());
		this.getModelObject().search();

	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		
		this.setTitle("Generar Equipos");
		this.setTaskDescription("Elija una operación a realizar");
		super.createMainTemplate(mainPanel);
		
		this.createResultsGrid(mainPanel);
		this.createGridActions(mainPanel);
		
	}
		

	@Override
	protected void addActions(Panel actionsPanel) {
		
		//botones del panel principal
		
	}
	
	protected void createResultsGrid(Panel mainPanel) {
		
		Table<Partido> table = new Table<Partido>(mainPanel, Partido.class);
		table.setHeigth(200);
		table.setWidth(450);

		table.bindItemsToProperty("resultados");
		table.bindValueToProperty("partidoSeleccionado");

		this.describeResultsGrid(table);
	}
	
	protected void describeResultsGrid(Table<Partido> table) {
		
		new Column<Partido>(table) //
			.setTitle("Fecha y Hora")
			.setFixedSize(250)
			.bindContentsToProperty("fechaHora");

		new Column<Partido>(table) //
			.setTitle("Lugar")
			.setFixedSize(100)
			.bindContentsToProperty("lugar");

	
	}
	
	protected void createGridActions(Panel mainPanel) {
		
		Panel actionsPanel = new Panel(mainPanel);
		actionsPanel.setLayout(new HorizontalLayout());

		Button edit = new Button(actionsPanel);
		edit.setCaption("Generar");
		edit.onClick(new MessageSend(this, "generar"));

		// Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
		NotNullObservable elementSelected = new NotNullObservable("partidoSeleccionado");
		edit.bindEnabled(elementSelected);
	}
	
	
	public void generar() {
		this.openDialog(new EquiposGeneradosWindow(this));
	}
	

	private void openDialog(EquiposGeneradosWindow equiposGeneradosWindow) {
		equiposGeneradosWindow.open();
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		Panel searchFormPanel = new Panel(mainPanel);
		
		//mainPanel.setLayout(new VerticalLayout());
		
		new Label(mainPanel).setText("Criterios de Ordenamiento");
		RadioSelector<String> radioSelectorOrdenamiento=new RadioSelector<>(mainPanel);
		radioSelectorOrdenamiento.setWidth(20);
		radioSelectorOrdenamiento.bindValueToProperty("ordenamientoSeleccionado");
		radioSelectorOrdenamiento.bindItemsToProperty("ordenamientos");
		
		//new Label(mainPanel).setText("Criterios de Ordenamiento");
		//new Label(mainPanel).bindValueToProperty("ordenamientoSeleccionado");
		
		new Label(mainPanel).setText("Criterios de Selección");
		RadioSelector<String> radioSelectorSeleccion=new RadioSelector<>(mainPanel);
		radioSelectorSeleccion.setWidth(20);
		radioSelectorSeleccion.bindValueToProperty("seleccionSeleccionada");
		radioSelectorSeleccion.bindItemsToProperty("selecciones");		
		
	}
}
