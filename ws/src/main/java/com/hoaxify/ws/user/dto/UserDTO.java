package com.hoaxify.ws.user.dto;

import com.hoaxify.ws.user.User;

public class UserDTO {
    
   
    Long id;

    String username;

    String email;

    String image;

    

    public UserDTO(User user){
      setId(user.getId());
      setUsername(user.getUsername());
      setEmail(user.getEmail());
      
      setImage(user.getImage());  
    }

    

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
}
