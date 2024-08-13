package com.hoaxify.ws.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.shared.GenericMessage;
import com.hoaxify.ws.shared.Messages;
import com.hoaxify.ws.user.dto.UserCreate;
import com.hoaxify.ws.user.dto.UserDTO;
import com.hoaxify.ws.user.exception.ActivationNotificationException;
import com.hoaxify.ws.user.exception.InvalidTokenException;
import com.hoaxify.ws.user.exception.NotUniqueEmailException;

import jakarta.validation.Valid;





@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("api/v1/users")
    GenericMessage createUser(@Valid @RequestBody UserCreate user) {
      userService.save(user.toUser());
      String message = Messages.getMessageForLocale("hoaxify.create.user.success.message", LocaleContextHolder.getLocale());
      return new GenericMessage(message);
    } 

    @PatchMapping("/api/v1/users/{token}/active")
    GenericMessage activateUser(@PathVariable String token) {
     userService.activateToken(token);
     String message = Messages.getMessageForLocale("hoaxify.activate.user.success.message", LocaleContextHolder.getLocale());
     return new GenericMessage(message);
   } 

    @GetMapping("/api/v1/users")
    Page<UserDTO> getUsers(Pageable pageable) {
        return userService.getUsers(pageable).map(UserDTO::new);
    }

    
    

    
     @ExceptionHandler(MethodArgumentNotValidException.class)
     ResponseEntity<ApiError> handleMethodArgNotValidEx(MethodArgumentNotValidException exception){

         ApiError apiError = new ApiError();
          apiError.setPath("/api/v1/users");
          String message = Messages.getMessageForLocale("hoaxify.error.validation", LocaleContextHolder.getLocale());
          apiError.setMessage(message);
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
          apiError.setMessage(exception.getMessage());
          apiError.setStatus(400);
          apiError.setValidationErrors(exception.getValidationErrors());
          return ResponseEntity.badRequest().body(apiError);
     }

     @ExceptionHandler(ActivationNotificationException.class)
     ResponseEntity<ApiError> handleActivationNotificationException(ActivationNotificationException exception){

          ApiError apiError = new ApiError();
          apiError.setPath("/api/v1/users");
          apiError.setMessage(exception.getMessage());
          apiError.setStatus(502);
          return ResponseEntity.status(502).body(apiError);
     }

     @ExceptionHandler(InvalidTokenException.class)
     ResponseEntity<ApiError> handleInvalidTokenException(InvalidTokenException exception){

          ApiError apiError = new ApiError();
          apiError.setPath("/api/v1/users");
          apiError.setMessage(exception.getMessage());
          apiError.setStatus(400);
          return ResponseEntity.status(400).body(apiError);
     }

     
     
}
