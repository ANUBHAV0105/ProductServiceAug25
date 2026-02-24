package com.projects.productserviceaug25.commons;

import com.projects.productserviceaug25.dtos.UserDto;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class AuthCommons {
    private static RestTemplate restTemplate;
    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public static boolean validateToken(String tokenValue){
        //Call UserService
        UserDto userDto = restTemplate.getForObject("http://localhost:8080/users/validate/"+tokenValue, UserDto.class);
        if (userDto == null){
            return false;
        }
        return true;
    }
}
