package com.irdeto.rrm.ls.edgeservices;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("playready-service")
public interface ProtectionSystemProfileService {

  @RequestMapping(method = RequestMethod.GET, value = "/protectionSystems",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  PagedResources<String> findAllProfiles();

  @RequestMapping(method = RequestMethod.POST, value = "/protectionSystems",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  String createSystemProfile(@RequestBody String systemProfile);
}
