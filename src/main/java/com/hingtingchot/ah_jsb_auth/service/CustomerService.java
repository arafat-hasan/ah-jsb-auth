package com.hingtingchot.ah_jsb_auth.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hingtingchot.ah_jsb_auth.domain.Address;
import com.hingtingchot.ah_jsb_auth.domain.Customer;
import com.hingtingchot.ah_jsb_auth.model.CustomerDTO;
import com.hingtingchot.ah_jsb_auth.repos.AddressRepository;
import com.hingtingchot.ah_jsb_auth.repos.CustomerRepository;
import com.hingtingchot.ah_jsb_auth.util.NotFoundException;
import com.hingtingchot.ah_jsb_auth.util.ReferencedWarning;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerService(final CustomerRepository customerRepository,
            final AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public List<CustomerDTO> findAll() {
        final List<Customer> customers = customerRepository.findAll(Sort.by("id"));
        return customers.stream()
                .map(customer -> mapToDTO(customer, new CustomerDTO()))
                .toList();
    }

    public CustomerDTO get(final Long id) {
        return customerRepository.findById(id)
                .map(customer -> mapToDTO(customer, new CustomerDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CustomerDTO customerDTO) {
        final Customer customer = new Customer();
        mapToEntity(customerDTO, customer);
        return customerRepository.save(customer).getId();
    }

    public void update(final Long id, final CustomerDTO customerDTO) {
        final Customer customer = customerRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(customerDTO, customer);
        customerRepository.save(customer);
    }

    public void delete(final Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO mapToDTO(final Customer customer, final CustomerDTO customerDTO) {
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setGender(customer.getGender());
        customerDTO.setDob(customer.getDob());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setSecondaryPhone(customer.getSecondaryPhone());
        customerDTO.setNid(customer.getNid());
        customerDTO.setOccupation(customer.getOccupation());
        customerDTO.setOrganization(customer.getOrganization());
        customerDTO.setProfilePicURL(customer.getProfilePicURL());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setLastResetAt(customer.getLastResetAt());
        customerDTO.setCreatedAt(customer.getCreatedAt());
        customerDTO.setStatus(customer.getStatus());
        customerDTO.setUpdatedAt(customer.getUpdatedAt());
        customerDTO.setUpdatedBy(customer.getUpdatedBy());
        return customerDTO;
    }

    private Customer mapToEntity(final CustomerDTO customerDTO, final Customer customer) {
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setGender(customerDTO.getGender());
        customer.setDob(customerDTO.getDob());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());
        customer.setSecondaryPhone(customerDTO.getSecondaryPhone());
        customer.setNid(customerDTO.getNid());
        customer.setOccupation(customerDTO.getOccupation());
        customer.setOrganization(customerDTO.getOrganization());
        customer.setProfilePicURL(customerDTO.getProfilePicURL());
        customer.setPassword(customerDTO.getPassword());
        customer.setLastResetAt(customerDTO.getLastResetAt());
        customer.setCreatedAt(customerDTO.getCreatedAt());
        customer.setStatus(customerDTO.getStatus());
        customer.setUpdatedAt(customerDTO.getUpdatedAt());
        customer.setUpdatedBy(customerDTO.getUpdatedBy());
        return customer;
    }

    public boolean emailExists(final String email) {
        return customerRepository.existsByEmailIgnoreCase(email);
    }

    public boolean phoneExists(final String phone) {
        return customerRepository.existsByPhoneIgnoreCase(phone);
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Customer customer = customerRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Address userAddress = addressRepository.findFirstByUser(customer);
        if (userAddress != null) {
            referencedWarning.setKey("customer.address.user.referenced");
            referencedWarning.addParam(userAddress.getId());
            return referencedWarning;
        }
        return null;
    }

}
