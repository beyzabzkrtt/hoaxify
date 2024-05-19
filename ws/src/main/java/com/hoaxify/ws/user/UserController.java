package com.hoaxify.ws.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.ws.user.shared.GenericMessage;



@RestController
@CrossOrigin(origins = "http://localhost:5174")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("api/v1/users")
    GenericMessage createUser(@RequestBody User user) {
      userService.save(user);
      return new GenericMessage("User is created");
    }
    

    
}
