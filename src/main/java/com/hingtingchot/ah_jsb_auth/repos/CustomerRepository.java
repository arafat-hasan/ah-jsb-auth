package com.hingtingchot.ah_jsb_auth.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hingtingchot.ah_jsb_auth.domain.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneIgnoreCase(String phone);

    Optional<Customer> findByEmail(String email);

}
