package za.co.itmathapi.Controller;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("api/")
public class ITMathematicsController {
	
	@Autowired
	private JavaMailSender itmathMailSender;
	
	private SimpleMailMessage itmathMailMessage = new SimpleMailMessage();
	/*
	 * Function to test if api is online
	 */
	@GetMapping("/")
	private String check() {
		return "I am Online";
	}
	
	/*
	 * Function to send app recommendation
	 */
	@PostMapping("recommendation")
	public String myrecommendation(@RequestBody JsonNode myRecom) {
		
		JSONObject rFeedback = new JSONObject();
		
		String recomandation = myRecom.get("Recommendation").asText();
		
		System.out.println(recomandation);
		
		itmathMailMessage.setFrom("tsaomegatech@outlook.com");
		itmathMailMessage.setTo("andrieschimule@gmail.com");
		itmathMailMessage.setSubject("IT Mathematics App Recommendation");
		itmathMailMessage.setText("Hello sir, your bot here" + "\n\n\nYou've recived the following recommendation:\n\n" + recomandation+"\n\n\nRegards\nYour Trusted Bot");
		
		itmathMailSender.send(itmathMailMessage);
		rFeedback.put("Feedback", "Thank for Your recommendation, it has been sent to Andries Chimule :->)");
		return rFeedback.toString();
	}
	
	@PostMapping("message")
	public String myMessage(@RequestBody JsonNode theContact) {
		JSONObject mFeedback = new JSONObject();
		
		String name = theContact.get("Name").asText();
		String email = theContact.get("Email").asText();
		String message = theContact.get("Message").asText();
		String subject = theContact.get("Subject").asText();
		String msgDate = theContact.get("Date").asText();
		
		itmathMailMessage.setFrom("tsaomegatech@outlook.com");
		itmathMailMessage.setTo("andrieschimule@gmail.com");
		itmathMailMessage.setSubject(subject);
		itmathMailMessage.setText("Hello sir, your bot here" + 
								  "\n\nYou've recived the following message from: "+name+"\n\n"
								  		+ "The message is: \n\n" + message+"\n\nEmail: "+email+"\n\nDate: "+msgDate+ "\n\n\nRegards\nYour Trusted Bot");
		
		itmathMailSender.send(itmathMailMessage);
		mFeedback.put("Feedback", "Thank you, Your message has been successfully sent to Andries Chimule");
		
		return mFeedback.toString();
			
	}
	
	
}

