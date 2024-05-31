package com.hingtingchot.ah_auth.repos;

import com.hingtingchot.ah_auth.domain.Address;
import com.hingtingchot.ah_auth.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findFirstByUser(Customer customer);

    boolean existsByLabelIgnoreCase(String label);

}
