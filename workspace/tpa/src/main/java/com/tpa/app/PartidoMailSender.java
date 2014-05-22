package com.tpa.app;

public class PartidoMailSender implements MailSender {
	private MailSender mailSender;
	private String remitente;
	private String prefijoDelAsunto;

	public PartidoMailSender(Partido partido) {
		this.mailSender = partido.getMailSender();
		this.remitente = "partidos-grupo13@dds.utn.frba";
		this.prefijoDelAsunto = partido.toString() + ":";
	}

	@Override
	public void enviarMail(Mail mail) {
		mailSender.enviarMail(new Mail(prefijoDelAsunto + mail.getTitulo(),
				mail.getBody(), remitente, mail.getDestinatario()));
	}
}
