package com.tpa.app.model;

public class Mail {
	private final String titulo;
	private final String body;
	private final String remitente;
	private final String destinatario;

	public Mail(String titulo, String body, String remitente,
			String destinatario) {
		this.titulo = titulo;
		this.body = body;
		this.remitente = remitente;
		this.destinatario = destinatario;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getBody() {
		return body;
	}

	public String getRemitente() {
		return remitente;
	}

	public String getDestinatario() {
		return destinatario;
	}
}