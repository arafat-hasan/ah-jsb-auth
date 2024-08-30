package com.hingtingchot.ah_jsb_auth.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hingtingchot.ah_jsb_auth.domain.Address;
import com.hingtingchot.ah_jsb_auth.domain.Customer;


public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findFirstByUser(Customer customer);

    boolean existsByLabelIgnoreCase(String label);

}
