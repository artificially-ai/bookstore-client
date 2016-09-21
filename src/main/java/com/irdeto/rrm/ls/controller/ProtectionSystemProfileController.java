package com.irdeto.rrm.ls.controller;

import com.irdeto.rrm.ls.edgeservices.ProtectionSystemProfileService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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
@RequestMapping("/protectionSystems")
public class ProtectionSystemProfileController {

  private final static Logger LOGGER = Logger.getLogger(ProtectionSystemProfileController.class.getName());

  private ProtectionSystemProfileService systemProfileService;

  private ModelMapper modelMapper;

  @Autowired
  public ProtectionSystemProfileController(
      final ProtectionSystemProfileService systemProfileService, final ModelMapper modelMapper) {
    this.systemProfileService = systemProfileService;
    this.modelMapper = modelMapper;
  }

  @HystrixCommand(fallbackMethod = "listAllFallback")
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Collection<String> listAll() {
    PagedResources<String> resources = systemProfileService.findAllProfiles();
    Collection<String> profiles = resources.getContent();

    return profiles;
  }

  public Collection<String> listAllFallback() {
    LOGGER.log(Level.SEVERE, "The circuit is open for the listAll() method call.");
    return new ArrayList<>();
  }
}