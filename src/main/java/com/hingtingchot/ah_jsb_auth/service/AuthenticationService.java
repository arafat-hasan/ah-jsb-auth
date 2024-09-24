package com.hingtingchot.ah_jsb_auth.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hingtingchot.ah_jsb_auth.domain.Customer;
import com.hingtingchot.ah_jsb_auth.model.CustomerDTO;
import com.hingtingchot.ah_jsb_auth.model.LoginDTO;
import com.hingtingchot.ah_jsb_auth.repos.CustomerRepository;

@Service
public class AuthenticationService {
    private final CustomerRepository customerRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        CustomerRepository customerRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer signup(CustomerDTO input) {
        Customer customer = new Customer();
        customer.setFirstName(input.getFirstName());
        customer.setEmail(input.getEmail());
        customer.setPassword(passwordEncoder.encode(input.getPassword()));
        return customerRepository.save(customer);
    }

    public Customer authenticate(LoginDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return customerRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
