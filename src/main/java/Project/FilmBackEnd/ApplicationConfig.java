package Project.FilmBackEnd;


import org.springframework.context.annotation.Configuration;
import com.google.gson.Gson;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.regions.Region;

import javax.sql.DataSource;




@Configuration
public class ApplicationConfig {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretkey;


    private Gson gson = new Gson();

    @Bean
    public DataSource dataSource() {
        AwsSecrets secrets = getSecret();
        return DataSourceBuilder
                .create()
                //  .driverClassName("com.mysql.cj.jdbc.driver")
                .url("jdbc:" + secrets.getEngine() + "://" + secrets.getHost() + ":" + secrets.getPort() + "/sakila?useSSL=false&allowPublicKeyRetrieval=true")
                .username(secrets.getUsername())
                .password(secrets.getPassword())
                .build();
    }


    private AwsSecrets getSecret() {

        String secretName = "filmdbsecret";
        String region = "us-east-1";

        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretkey)))
                .build();

        String secret, decodedBinarySecret;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = null;

        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            throw e;
        }
        if (getSecretValueResult.getSecretString() != null) {
            secret = getSecretValueResult.getSecretString();
            return gson.fromJson(secret, AwsSecrets.class);
        }


        return null;
    }

}