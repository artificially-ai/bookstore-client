package nl.ekholabs.microservices.bookstore.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.integration.annotation.IntegrationComponentScan;

@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableHystrix
@EnableFeignClients
@EnableZuulProxy
@EnableCircuitBreaker
@EnableEurekaClient
@EnableDiscoveryClient
@IntegrationComponentScan
@SpringBootApplication
public class BookClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookClientApplication.class, args);
  }
}
