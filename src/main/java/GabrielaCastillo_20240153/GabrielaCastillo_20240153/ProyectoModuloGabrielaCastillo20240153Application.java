package GabrielaCastillo_20240153.GabrielaCastillo_20240153;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoModuloGabrielaCastillo20240153Application {

	public static void main(String[] args) {

		Dotenv	dotenv = Dotenv.configure().ignoreIfMissing().load();

		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(),
						entry.getValue())
				);
		SpringApplication.run(ProyectoModuloGabrielaCastillo20240153Application.class, args);
	}

}
