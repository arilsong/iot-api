package com.arilsongomes.iotapi.controllers;

import com.arilsongomes.iotapi.dto.UserDto;
import com.arilsongomes.iotapi.entity.User;
import com.arilsongomes.iotapi.services.user.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/v1/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping()
    public ResponseEntity<String> creteUser(@RequestBody @Valid UserDto userDto){
        userService.createUser(userDto);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
}
