package com.epam.restcontrollers;

import com.epam.dto.UserDto;
import com.epam.exceptions.UserNotFoundException;
import com.epam.model.AuthenticationRequest;
import com.epam.model.AuthenticationResponse;
import com.epam.services.UserDetailsServiceImpl;
import com.epam.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserRestController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Operation(description = "It registers a user and returns true if registered and false if the user is already present")
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping("/users/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userDetailsService.registerUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new UserNotFoundException("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}
