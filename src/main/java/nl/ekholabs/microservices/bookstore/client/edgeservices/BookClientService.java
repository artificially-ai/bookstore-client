package nl.ekholabs.microservices.bookstore.client.edgeservices;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("bookstore-service")
public interface BookClientService {

  @RequestMapping(method = RequestMethod.GET, value = "/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  PagedResources<String> findAll();

  @RequestMapping(method = RequestMethod.POST, value = "/books", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  String create(@RequestBody String book);
}
