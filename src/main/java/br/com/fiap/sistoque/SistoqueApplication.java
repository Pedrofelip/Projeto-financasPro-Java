package br.com.fiap.sistoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class SistoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistoqueApplication.class, args);
	}
	
	@RequestMapping
	@ResponseBody
	public String home() {
		return "SisToque";
	}
}
