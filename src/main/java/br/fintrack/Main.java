package br.fintrack;

import br.fintrack.configs.DynamoDBConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@Import({DynamoDBConfig.class})
@ComponentScan(basePackages = {"br.fintrack"})
@OpenAPIDefinition(info = @Info(title = "Fin Track Investimentos API", version = "1.0", description = "API de Investimentos"))
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Inicializando...");
        System.setProperty("server.servlet.context-path", "/finTrack-investiment-api");
        new SpringApplicationBuilder(Main.class).web(WebApplicationType.SERVLET).run(args);
    }
}