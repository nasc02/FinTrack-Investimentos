package br.fintrack.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.fintrack.models.Investiment;
import br.fintrack.repositories.InvestimentRepository;
import io.swagger.v3.oas.models.security.SecurityScheme;
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
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, InvestimentTests.DynamoDBConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InvestimentTests {

    private static Logger LOGGER = LoggerFactory.getLogger(UserTests.class);
    private SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");

    @Configuration
    @EnableDynamoDBRepositories(basePackageClasses = {InvestimentRepository.class})
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
    private InvestimentRepository repository;

    @Test
    public void teste1Criacao() throws ParseException {
        LOGGER.info("Criando objetos...");
        Investiment i1 = new Investiment("asdnao1238080ad", BigDecimal.valueOf(152300), "Poupança", "Ativo", new Date());
        Investiment i2 = new Investiment("asdnao1238080ad", BigDecimal.valueOf(3252155), "CDI", "Ativo", new Date());
        Investiment i3 = new Investiment("asdnao1238080ad", BigDecimal.valueOf(1523232100), "Imoveis", "Ativo", new Date());


        repository.save(i1);
        repository.save(i2);
        repository.save(i3);

        Iterable<Investiment> lista = repository.findAll();
        assertNotNull(lista.iterator());
        for (Investiment investiment : lista) {
            LOGGER.info(investiment.toString());
        }
        LOGGER.info("Pesquisado um objeto");
        List<Investiment> result = repository.findByuserId("asdnao1238080ad");
        assertEquals(result.size(), 3);
        LOGGER.info("Encontrado: {}", result.size());
    }

    @Test
    public void teste2Exclusao() throws ParseException {
        LOGGER.info("Excluindo objetos...");
        List<Investiment> result = repository.findByuserId("asdnao1238080ad");
        for (Investiment investiment : result) {
            LOGGER.info("Excluindo Investiment id = "+ investiment.getId());
            repository.delete(investiment);
        }
        result = repository.findByuserId("asdnao1238080ad");
        assertEquals(result.size(), 0);
        LOGGER.info("Exclusão feita com sucesso");
    }


}