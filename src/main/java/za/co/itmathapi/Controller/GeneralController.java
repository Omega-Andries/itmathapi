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
		
		String clientName = theReasons.get("Client_Name").asText();
		String clientAppName = theReasons.get("App_Name").asText();
		String clientEmail = theReasons.get("Client_Email").asText();
		String ReasonForReject = theReasons.get("Reject_Reason").asText();
		generalMailMessage.setFrom("tsaomegatech@outlook.com");
		generalMailMessage.setSubject("No Reply - Application Rejected");
		generalMailMessage.setTo(clientEmail);
		
		String theMessage = "Greetings " + clientName + "\n\nI am a bot from TsaOmega Technologies (name = The_Dries)\n\n\n" 
						+	"Your request for the application with the name " + clientAppName + " has been denied, the reason for this rejection is:\n"
						+   ReasonForReject + "\n\nIf you have any questions, please email: andrieschimule@gmail.com \n\n We'd love to hear from you again\n\n" 
						+   "Kind Regards\nTsaOmega Technologies";
		generalMailMessage.setReplyTo("andrieschimule@gmail.com");
		generalMailMessage.setText(theMessage);
		
		generalMailSender.send(generalMailMessage);
		statusJSN.put("Status", "Email Sent");
		return statusJSN.toString();
	}
	
}
