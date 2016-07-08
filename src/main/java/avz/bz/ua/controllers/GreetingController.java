package avz.bz.ua.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

//@Controller
public class GreetingController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public String greeting(String message) throws Exception {
		System.out.println(message);
		Thread.sleep(3000); // simulated delay
		return ("Hello, " + message + "!");
	}

}
