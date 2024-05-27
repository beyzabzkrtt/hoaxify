package com.hoaxify.ws.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.shared.GenericMessage;

import jakarta.validation.Valid;



@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("api/v1/users")
    GenericMessage createUser(@Valid @RequestBody User user) {
      userService.save(user);
      return new GenericMessage("User is created");
    } 
    
     @ExceptionHandler(MethodArgumentNotValidException.class)
     ResponseEntity<ApiError> handleMethodArgNotValidEx(MethodArgumentNotValidException exception){

         ApiError apiError = new ApiError();
          apiError.setPath("/api/v1/users");
          apiError.setMessage("validation error");
          apiError.setStatus(400);
          Map<String,String> validationErrors = new HashMap<>();
          for(var fieldError : exception.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
          }
          apiError.setValidationErrors(validationErrors);
          return ResponseEntity.badRequest().body(apiError);
     }

     @ExceptionHandler(NotUniqueEmailException.class)
     ResponseEntity<ApiError> handleNotUniqueEmailEx(NotUniqueEmailException exception){

         ApiError apiError = new ApiError();
          apiError.setPath("/api/v1/users");
          apiError.setMessage("validation error");
          apiError.setStatus(400);
          Map<String,String> validationErrors = new HashMap<>();
          validationErrors.put("email","E-mail in use");
          apiError.setValidationErrors(validationErrors);
          return ResponseEntity.badRequest().body(apiError);
     }

     
}
