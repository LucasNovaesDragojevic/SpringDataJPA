package br.com.springdata;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.springdata.service.CrudCargoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;
	
	private CrudCargoService cargoService;
	
	public SpringDataApplication(CrudCargoService cargoService) {
		this.cargoService = cargoService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (system) {
			System.out.println("Qual ação você quer executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");		
			Integer action = scanner.nextInt();
			if (action == 1) {
				cargoService.inicial(scanner);
			} else {
				system = false;
			}
		}
	}

}
