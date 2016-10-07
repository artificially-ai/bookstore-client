package nl.ekholabs.microservices.bookstore.client.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import nl.ekholabs.microservices.bookstore.client.edgeservices.BookClientService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/books")
public class BookClientController {

  private final static Logger LOGGER = Logger.getLogger(BookClientController.class.getName());

  private BookClientService bookClientService;

  private ModelMapper modelMapper;

  @Autowired
  public BookClientController(
          final BookClientService bookClientService, final ModelMapper modelMapper) {
    this.bookClientService = bookClientService;
    this.modelMapper = modelMapper;
  }

  @HystrixCommand(fallbackMethod = "listAllFallback")
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Collection<String> listAll() {
    PagedResources<String> resources = bookClientService.findAll();
    Collection<String> profiles = resources.getContent();

    return profiles;
  }

  public Collection<String> listAllFallback() {
    LOGGER.log(Level.SEVERE, "The circuit is open for the listAll() method call.");
    return new ArrayList<>();
  }
}