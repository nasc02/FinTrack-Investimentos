package br.fintrack.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.fintrack.models.User;
import br.fintrack.repositories.UserRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, UserTests.DynamoDBConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTests {

    private static Logger LOGGER = LoggerFactory.getLogger(UserTests.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");

    @Configuration
    @EnableDynamoDBRepositories(basePackageClasses = {UserRepository.class})
    public static class DynamoDBConfig {

        @Value("${amazon.aws.accesskey}")
        private String amazonAWSAccessKey;

        @Value("${amazon.aws.secretkey}")
        private String amazonAWSSecretKey;

        public AWSCredentialsProvider amazonAWSCredentialsProvider() {
            return new AWSStaticCredentialsProvider(amazonAWSCredentials());
        }

        @Bean
        public AWSCredentials amazonAWSCredentials() {
            return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
        }

        @Bean
        public AmazonDynamoDB amazonDynamoDB() {
            return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
                    .withRegion(Regions.US_EAST_1).build();
        }
    }

    @Autowired
    private UserRepository repository;

    @Test
    public void teste1Criacao() throws ParseException {
        LOGGER.info("Criando objetos...");
        User u1 = new User("example@example.com", "nomeTeste", new Date(1990, 5, 15), "senha123");
        User u2 = new User("example@example.com", "nomeTeste1", new Date(1990, 6, 15), "senha1234");
        User u3 = new User("example@example.com", "nomeTeste2", new Date(1990, 7, 15), "senha12345");

        repository.save(u1);
        repository.save(u2);
        repository.save(u3);

        Iterable<User> lista = repository.findAll();
        assertNotNull(lista.iterator());
        for (User user : lista) {
            LOGGER.info(user.toString());
        }
        LOGGER.info("Pesquisado um objeto");
        List<User> result = repository.findByEmail("example@example.com");
        assertEquals(result.size(), 3);
        LOGGER.info("Encontrado: {}", result.size());
    }

    @Test
    public void teste2Exclusao() throws ParseException {
        LOGGER.info("Excluindo objetos...");
        List<User> result = repository.findByEmail("example@example.com");
        for (User user : result) {
            LOGGER.info("Excluindo User id = "+ user.getId());
            repository.delete(user);
        }
        result = repository.findByEmail("example@example.com");
        assertEquals(result.size(), 0);
        LOGGER.info("Exclusão feita com sucesso");
    }


}