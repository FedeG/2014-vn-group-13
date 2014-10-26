package com.tpa.app.view;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;	
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import com.tpa.app.model.Administrador;
import com.tpa.app.viewModel.PantallaPrincipal;

@SuppressWarnings("serial")
public class PantallaPrincipalWindow extends SimpleWindow<PantallaPrincipal> {

	public PantallaPrincipalWindow(WindowOwner parent) {
		super(parent, new PantallaPrincipal());
	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Organizador de Partidos de Futbol - DDS 2014");
		this.setTaskDescription("Elija una operacion a realizar");
		super.createMainTemplate(mainPanel);
	}
	@Override
	protected void createFormPanel(Panel mainPanel){
		
		mainPanel.setLayout(new VerticalLayout());
		
		new Label(mainPanel).setText("- Operaciones -").setWidth(430);
		Panel panelSelectAdm = new Panel(mainPanel);
		panelSelectAdm.setLayout(new HorizontalLayout());
		new Label(panelSelectAdm).setText("Seleccione Administrador:").setWidth(200);
		Selector<Administrador> selector = new Selector<Administrador>(panelSelectAdm) //
				.allowNull(false);
		selector.setWidth(230);
		selector.bindItemsToProperty("administradores");
		selector.bindValueToProperty("administradorSeleccionado");
			
		Button boton = new Button(mainPanel)
			.setCaption("Generar Equipos")
			.onClick(new MessageSend(this, "generarEquipos"));
		boton.bindEnabled(new NotNullObservable("administradorSeleccionado"));
	
		boton = new Button(mainPanel)
			.setCaption("Buscar Jugadores")
			.onClick(new MessageSend(this, "buscarJugadores"));
		boton.bindEnabled(new NotNullObservable("administradorSeleccionado"));
	}
	
	@Override
	protected void addActions(Panel actionsPanel) {
	}
	
	public void generarEquipos() {
		new GenerarEquiposWindow(this, this.getModelObject().getAdministradorSeleccionado()).open();
	}

	public void buscarJugadores() {
		new BuscarJugadoresWindow(this).open();
	}
}
