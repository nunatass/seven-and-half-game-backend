package com.sevenandhalf.controller;

import com.sevenandhalf.exception.ConflictException;
import com.sevenandhalf.exception.NotFoundException;
import com.sevenandhalf.exception.UnAuthorizedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

  @RequestMapping(value = "/exceptions", method = RequestMethod.GET)
  public String testExceptionHandling(@RequestParam int code) {
    switch (code) {
      case 401:
        throw new UnAuthorizedException("You are not authorized");
      case 404:
        throw new NotFoundException("Requested resource is not found.");
      case 409:
        throw new ConflictException("Resource already exists in DB.");

      default:
        return "Yeah...No Exception.";

    }
  }
}
