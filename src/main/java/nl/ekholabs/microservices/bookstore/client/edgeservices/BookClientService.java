package nl.ekholabs.microservices.bookstore.client.edgeservices;

import nl.ekholabs.microservices.bookstore.domain.BookDTO;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("bookstore-service")
public interface BookClientService {

  @RequestMapping(method = RequestMethod.GET, value = "/books")
  PagedResources<BookDTO> findAll();

  @RequestMapping(method = RequestMethod.POST, value = "/books")
  Resource<BookDTO> save(@RequestBody BookDTO book);
}
