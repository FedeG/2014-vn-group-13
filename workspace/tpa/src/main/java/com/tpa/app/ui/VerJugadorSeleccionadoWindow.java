package com.tpa.app.ui;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.aop.windows.TransactionalDialog;
import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import org.uqbar.lacar.ui.model.ListBuilder;
import org.uqbar.lacar.ui.model.bindings.Binding;

import com.tpa.app.Jugador;
import com.tpa.app.domain.PantallaPrincipal;

public class VerJugadorSeleccionadoWindow extends  TransactionalDialog<Jugador> {

	public VerJugadorSeleccionadoWindow(WindowOwner parent, Jugador jugador) {
		super(parent, jugador);

	}

	
	@Override
	protected void createMainTemplate(Panel mainPanel) {
		
		this.setTitle("Ver Jugador Seleccionado");
		super.createMainTemplate(mainPanel);
		
		

	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));

		new Label(form).setText("Nombre");
		new TextBox(form).bindValueToProperty("nombre");
		
		new Label(form).setText("Apodo");
		new TextBox(form).bindValueToProperty("apodo");
		
		new Label(form).setText("Handicap");
		new TextBox(form).bindValueToProperty("handicap");
		
		new Label(form).setText("Promedio");
		new TextBox(form).bindValueToProperty("promedio");
		
		

//		new Label(form).setText("Nombre del cliente");
//		new TextBox(form).bindValueToProperty("nombre");
//
//		new Label(form).setText("Modelo del aparato");
//		
//		Selector<ModeloCelular> selector = new Selector<ModeloCelular>(form) //
//			.allowNull(false);
//		selector.bindValueToProperty("modeloCelular");
//
//		Binding<ListBuilder<ModeloCelular>> itemsBinding = selector.bindItems( //
//			new ObservableProperty(RepositorioModelos.getInstance(), "modelos"));
//
//		itemsBinding.setAdapter( //
//			new PropertyAdapter(ModeloCelular.class, "descripcionEntera"));
//
//		
//		new Label(form).setText("Recibe resumen cuenta en domicilio");
//		new CheckBox(form).bindValueToProperty("recibeResumenCuenta");

	}



	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel)
		.setCaption("Volver")
		.onClick(new MessageSend(this, "volver"));
	
		
	}

	public void volver() {
		this.close();
	}


}
