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
@RequestMapping("general")
public class GeneralController {
	@Autowired
	JavaMailSender generalMailSender;
	
	SimpleMailMessage generalMailMessage = new SimpleMailMessage();
	
	@GetMapping("")
	public String greet() {
		return "I am online";
	}
	
	@PostMapping("permanantlyreject")
	public String permanantRejectMail(@RequestBody JsonNode theReasons) {
		
		JSONObject statusJSN = new JSONObject();
		System.out.println("I'm in");
		//get the information
		String clientName = theReasons.get("Client_Name").asText();
		String clientAppName = theReasons.get("App_Name").asText();
		String clientEmail = theReasons.get("Client_Email").asText();
		String ReasonForReject = theReasons.get("Reject_Reason").asText();
		
		//set email reasoning
		generalMailMessage.setFrom("tsaomegatech@outlook.com");
		generalMailMessage.setSubject("No Reply - Application Rejected");
		generalMailMessage.setTo(clientEmail);
		generalMailMessage.setReplyTo("andrieschimule@gmail.com");
		
		//write the email
		String theMessage = "Greetings " + clientName + "\n\nI am a bot from TsaOmega Technologies (name = The_Dries)\n\n" 
						+	"Your request for the application with the name " + clientAppName + " has been denied,\n"
						+   "The reason from the company for this denial is:\n\n"
						+   ReasonForReject + "\n\nIf you have any questions, please email: andrieschimule@gmail.com \n\n We'd love to hear from you again\n\n" 
						+   "Kind Regards\nTsaOmega Technologies";
		
		//set the message and send the email.
		generalMailMessage.setText(theMessage);
		generalMailSender.send(generalMailMessage);
		
		statusJSN.put("Status", "Email Sent");
		
		return statusJSN.toString();
	}
	
	@PostMapping("freezereject")
	public String freezeRejectMail(@RequestBody JsonNode theReasons) {
		JSONObject statusJSN = new JSONObject();
		
		//get the information
		String clientName = theReasons.get("Client_Name").asText();
		String clientAppName = theReasons.get("App_Name").asText();
		String clientEmail = theReasons.get("Client_Email").asText();
		String ReasonForReject = theReasons.get("Reject_Reason").asText();
		
		//set email reasoning
		generalMailMessage.setFrom("tsaomegatech@outlook.com");
		generalMailMessage.setSubject("No Reply - Application Freezed");
		generalMailMessage.setTo(clientEmail);
		generalMailMessage.setReplyTo("andrieschimule@gmail.com");
		
		//write the email
		String theMessage = "Greetings " + clientName + "\n\nI am a bot from TsaOmega Technologies (name = The_Dries)\n\n" 
						+	"Your request for the application with the name " + clientAppName + " has been freezed, this means that the work towards it's creation has stopped."
						+   "The reason from the company for this freeze is:\n\n"
						+   ReasonForReject + "\n\nIf you have any questions, please email: andrieschimule@gmail.com \n\n We'd love to hear from you again\n\n" 
						+   "Kind Regards\nTsaOmega Technologies";
		
		//set the message and send the email.
		generalMailMessage.setText(theMessage);
		generalMailSender.send(generalMailMessage);
		
		statusJSN.put("Status", "Email Sent");
		return statusJSN.toString();
	}
	
	@PostMapping("pendingapp")
	public String pendingappemail(@RequestBody JsonNode theReasons) {
		JSONObject statusJSN = new JSONObject();
		
		//get the information
		String clientName = theReasons.get("Client_Name").asText();
		String clientAppName = theReasons.get("App_Name").asText();
		String clientEmail = theReasons.get("Client_Email").asText();
		
		//set email reasoning
		generalMailMessage.setFrom("tsaomegatech@outlook.com");
		generalMailMessage.setSubject("No Reply - Application Freezed");
		generalMailMessage.setTo(clientEmail);
		generalMailMessage.setReplyTo("andrieschimule@gmail.com");
		
		//write the email
		String theMessage = "Greetings " + clientName + "\n\nI am a bot from TsaOmega Technologies (name = The_Dries)\n\n" 
						+	"Thank you for your request of the application with the name " + clientAppName + ", Due to many requests of apps, we had to place it in the waiting list.\n\n"
						+   "Don't stress though, the second we are done reviewing it, we will reach out to you."
						+   "\n\nIf you have any questions, please email: andrieschimule@gmail.com \n\n We'd love to hear from you again\n\n" 
						+   "Kind Regards\nTsaOmega Technologies";
		
		//set the message and send the email.
		generalMailMessage.setText(theMessage);
		generalMailSender.send(generalMailMessage);
		
		statusJSN.put("Status", "Email Sent");
		return statusJSN.toString();
	}
	
	@PostMapping("acceptedapp")
	public String acceptedappemail(@RequestBody JsonNode theReasons) {
		JSONObject statusJSN = new JSONObject();
		
		//get the information
		String clientName = theReasons.get("Client_Name").asText();
		String clientAppName = theReasons.get("App_Name").asText();
		String clientEmail = theReasons.get("Client_Email").asText();
		
		//set email reasoning
		generalMailMessage.setFrom("tsaomegatech@outlook.com");
		generalMailMessage.setSubject("No Reply - Application Freezed");
		generalMailMessage.setTo(clientEmail);
		generalMailMessage.setReplyTo("andrieschimule@gmail.com");
		
		//write the email
		String theMessage = "Greetings " + clientName + "\n\nI am a bot from TsaOmega Technologies (name = The_Dries)\n\n" 
						+	"Thank you for your request of the application with the name " + clientAppName + ", it has been successfully accepted :->)\n\n"
						+   "We're reviewing it currently and we will reach out to you as soon as we have any questions."
						+   "\n\nIf you have any questions, please email: andrieschimule@gmail.com \n\n We'd love to hear from you again\n\n" 
						+   "Kind Regards\nTsaOmega Technologies";
		
		//set the message and send the email.
		generalMailMessage.setText(theMessage);
		generalMailSender.send(generalMailMessage);
		
		statusJSN.put("Status", "Email Sent");
		return statusJSN.toString();
	}
	
}
