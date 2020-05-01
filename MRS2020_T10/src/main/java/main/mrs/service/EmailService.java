package main.mrs.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import main.mrs.model.ZahtevReg;



@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	/*
	 * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
	 */
	@Autowired
	private Environment env;
	/*
	 * Anotacija za oznacavanje asinhronog zadatka
	 * Vise informacija na: https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#scheduling
	 */
	@Async
	public void sendNotificaitionAsync(ZahtevReg user) throws MailException, InterruptedException {
		System.out.println("Slanje emaila...");

		try {
			MimeMessage message = javaMailSender.createMimeMessage();
	
	        message.setSubject("Potvrda registracije na Klinici");
	        MimeMessageHelper helper;
	        helper = new MimeMessageHelper(message, true);
	        helper.setFrom(env.getProperty("spring.mail.username"));
	        helper.setTo(user.getEmail());
	        String text ="Pozdrav " + user.getIme() + ",<br>potvrdite email za aktivaciju vaseg naloga na sajtu Klinike.<br> <a href=http://localhost:8080/>Aktivacioni link</a>";
	        message.setContent(text,"text/html");
			javaMailSender.send(message);
			System.out.println("Email poslat!");
		}
		catch(Exception e) {
			System.out.println("Doslo je do greske...");
		}
		
		
	}
	
	@Async
	public void sendNotificaitionDeniedAsync(ZahtevReg user, String opis) throws MailException, InterruptedException {
		System.out.println("Slanje emaila...");

		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(user.getEmail());
			mail.setFrom(env.getProperty("spring.mail.username"));
			mail.setSubject("Zahtev za registracijom je odbijen");
			mail.setText("Pozdrav " + user.getIme() + ",\n\nzahtev Vam je odbijen iz razloga \n"+ opis);
			javaMailSender.send(mail);
			System.out.println("Email poslat!");
		}
		catch(Exception e) {
			System.out.println("Doslo je do greske...");
		}
		
		
	}

}
