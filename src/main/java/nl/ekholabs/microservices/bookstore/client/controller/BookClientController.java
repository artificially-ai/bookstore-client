package nl.ekholabs.microservices.bookstore.client.controller;

import com.google.common.collect.Lists;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import nl.ekholabs.microservices.bookstore.client.edgeservices.BookClientService;
import nl.ekholabs.microservices.bookstore.domain.BookDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/books")
public class BookClientController {

    private final static Logger LOGGER = Logger.getLogger(BookClientController.class.getName());

    private BookClientService bookClientService;

    @Autowired
    public BookClientController(final BookClientService bookClientService) {
        this.bookClientService = bookClientService;
    }

    @HystrixCommand(fallbackMethod = "listBooksFallback")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PagedResources<BookDTO> listBooks() {
        return bookClientService.findAll();
    }

    @HystrixCommand(fallbackMethod = "createBookFallback")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Resource<BookDTO> createBook(@RequestBody BookDTO book) {
        return bookClientService.save(book);
    }

    public PagedResources<BookDTO> listBooksFallback() {
        LOGGER.log(Level.SEVERE, "The circuit is open for the listBooks() method call.");
        List<BookDTO> books = Stream.of(BookDTO.createBookDTO("N/A", "N/A", "N/A")).collect(Collectors.toList());
        PagedResources<BookDTO> booksResource = new PagedResources(books, new PageMetadata(1l, 1l, 1l), new Link("http://localhost:9999"));
        return booksResource;
    }

    public Resource<BookDTO> createBookFallback(BookDTO book) {
        LOGGER.log(Level.SEVERE, "The circuit is open for the createBook() method call.");
        return new Resource(BookDTO.createBookDTO("N/A", "N/A", "N/A"), new Link(""));
    }
}