package com.epam.restcontrollers;

import com.epam.dto.UserDto;
import com.epam.exceptions.UserNotFoundException;
import com.epam.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    UserService userService;

    @Operation(description = "It validates user credentials")
    @ApiResponse(responseCode = "200", description = "Successful")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody @Valid UserDto userDto) throws UserNotFoundException {
        return new ResponseEntity<>(userService.validateCredentials(userDto), HttpStatus.OK);
    }

    @Operation(description = "It registers a user and returns true if registered and false if the user is already present")
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }
}
