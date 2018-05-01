package com.pos.lotto.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pos.lotto.model.User;

@Service
public class MailService {

	private JavaMailSender javaMailSender;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendEmail(User user, String pass) throws MailException {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("mgh.it.dhaka@gmail.com");
		mail.setSubject("Account Created");
		mail.setText("Your account has been created with email id: "+ user.getEmail()+ " and password: "+pass);
		javaMailSender.send(mail);
	}
}
