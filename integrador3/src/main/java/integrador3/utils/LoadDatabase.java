package integrador3.utils;

import integrador3.repository.CarreraRepository;
import integrador3.repository.EstudianteRepository;
import integrador3.repository.Estudiante_CarreraRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(@Qualifier("CSVReader") CSVReader csvReader) {
        return args -> {
            csvReader.populateDB();
        };
    }
}