package ca.sheridancollege.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ca.sheridancollege.Bean.DogBean;
import ca.sheridancollege.Bean.HandlerBean;

@Component
public class NotificationService {

	
	private JavaMailSender javaMainSender;
	
	
	public NotificationService(JavaMailSender javaMailSemder) {
		
		this.javaMainSender=javaMailSemder;
		// TODO Auto-generated constructor stub
	}


	public void SendNotification(HandlerBean handerBean) throws MailException {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(handerBean.getHemail());
		mail.setFrom("malavjani92@gmail.com");
		mail.setSubject("You Have Been Sucessfully Added As An Handler");

		
		javaMainSender.send(mail);
		
	}
public void SendNotificationforaddingDog(DogBean b) throws MailException {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(b.getEmail());
		mail.setFrom("thedogshowregi@gmail.com");
		mail.setSubject("Dog Sucessfully added");
		mail.setText("Your dog details are as below \n"
				 + "Entry Number: '"+b.getEntryNumber()+"\n"
				+ "Dog Name: '"+b.getDogName()+"\n"
				+ "Owner Name: '"+b.getOwnerName()+"\n"
				 + "Breed: '"+b.getBreed()+"\n"
				 + "Dog_group: '"+b.getGroup()+"\n"
				 + "Gender: '"+b.getGender()+"\n"
				 + "ranking: '"+b.getRanking()+"\n"
				+ "Days: '"+b.getDate()+"\n");
		         
		
		javaMainSender.send(mail);
		
	}
	}
	
