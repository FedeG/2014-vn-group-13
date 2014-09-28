package com.tpa.app;

public class PartidoMailSender extends MailSender {
	private String remitente;
	private String prefijoDelAsunto;

	public PartidoMailSender() {
		this.remitente = "partidos-grupo13@dds.utn.frba";
	}

	@Override
	public void enviarMail(Mail mail) {
		//mailSender.enviarMail(new Mail(prefijoDelAsunto + mail.getTitulo(),
		//		mail.getBody(), remitente, mail.getDestinatario()));
	}
}
