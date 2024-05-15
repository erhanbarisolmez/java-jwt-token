package com.security.jwttoken.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/private")
public class PrivateController {
  
  @GetMapping
  public String helloWorldPrivate(){
    return "hello world! from private endpoint";
  }
  
  @GetMapping("/user")
  public String helloWorldUserPrivate(){
    return "hello world! from user private endpoint";
  }

  @GetMapping("/admin")
  public String helloWorldAdminPrivate(){
    return "hello world! from admin private endpoint";
  }

  @GetMapping("/sadmin")
  public String helloWorldSAdminPrivate(){
    return "hello world! from sadmin private endpoint";
  }


}
