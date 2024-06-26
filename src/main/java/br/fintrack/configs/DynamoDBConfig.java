package br.fintrack.configs;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

/**
 * Configurações para acessar o AWS DynamoDB
 * Para rodar, antes sete a seguinte variável de ambiente: -Dspring.config.location=file:///C:/Users/henri/workspace/Java/FinTrackInvestment.configs/applications.properties
 * No diretório criado a cima crie o arquivo application.properties contendo as seguitnes variáveis:
 * amazon.aws.accesskey=
 * amazon.aws.secretkey=
 * @author jhcru,nasc02
 *
 */
@Configuration
@EnableDynamoDBRepositories(basePackages = "br.fintrack.repositories")
public class DynamoDBConfig {

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