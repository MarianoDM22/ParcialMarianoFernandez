package utn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utn.demo.model.Usuario;
import utn.demo.repositories.UsuarioRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);


		Usuario usuario;
		UsuarioRepository usuarioRepository;


	}

}
