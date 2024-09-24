package com.hingtingchot.ah_jsb_auth.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hingtingchot.ah_jsb_auth.domain.Customer;
import com.hingtingchot.ah_jsb_auth.model.CustomerDTO;
import com.hingtingchot.ah_jsb_auth.model.LoginDTO;
import com.hingtingchot.ah_jsb_auth.model.LoginResponse;
import com.hingtingchot.ah_jsb_auth.service.AuthenticationService;
import com.hingtingchot.ah_jsb_auth.service.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Customer> register(@RequestBody CustomerDTO registerUserDto) {
        Customer registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginDTO loginUserDto) {
        Customer authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
