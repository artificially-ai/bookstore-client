package nl.ekholabs.microservices.bookstore.client;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;

@EnableHystrix
@EnableFeignClients
@EnableZuulProxy
@EnableCircuitBreaker
@EnableEurekaClient
@EnableDiscoveryClient
@IntegrationComponentScan
@SpringBootApplication
public class PlayreadyClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(PlayreadyClientApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
