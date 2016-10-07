package nl.ekholabs.microservices.bookstore.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RefreshScope
@RestController
@RequestMapping("/message")
public class MessageScopeController {

  @Value("${application.message}")
  private String message;

  @RequestMapping(method = RequestMethod.GET)
  public String message() {
    return message;
  }
}