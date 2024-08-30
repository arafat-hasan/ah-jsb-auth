package com.hingtingchot.ah_jsb_auth.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hingtingchot.ah_jsb_auth.domain.Address;
import com.hingtingchot.ah_jsb_auth.domain.Customer;
import com.hingtingchot.ah_jsb_auth.model.AddressDTO;
import com.hingtingchot.ah_jsb_auth.repos.AddressRepository;
import com.hingtingchot.ah_jsb_auth.repos.CustomerRepository;
import com.hingtingchot.ah_jsb_auth.util.NotFoundException;


@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public AddressService(final AddressRepository addressRepository,
            final CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    public List<AddressDTO> findAll() {
        final List<Address> addresses = addressRepository.findAll(Sort.by("id"));
        return addresses.stream()
                .map(address -> mapToDTO(address, new AddressDTO()))
                .toList();
    }

    public AddressDTO get(final Long id) {
        return addressRepository.findById(id)
                .map(address -> mapToDTO(address, new AddressDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final AddressDTO addressDTO) {
        final Address address = new Address();
        mapToEntity(addressDTO, address);
        return addressRepository.save(address).getId();
    }

    public void update(final Long id, final AddressDTO addressDTO) {
        final Address address = addressRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(addressDTO, address);
        addressRepository.save(address);
    }

    public void delete(final Long id) {
        addressRepository.deleteById(id);
    }

    private AddressDTO mapToDTO(final Address address, final AddressDTO addressDTO) {
        addressDTO.setId(address.getId());
        addressDTO.setLabel(address.getLabel());
        addressDTO.setDivision(address.getDivision());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setUpazila(address.getUpazila());
        addressDTO.setLocalGovt(address.getLocalGovt());
        addressDTO.setAddressLine1(address.getAddressLine1());
        addressDTO.setAddressLine2(address.getAddressLine2());
        addressDTO.setLongitude(address.getLongitude());
        addressDTO.setLatitude(address.getLatitude());
        addressDTO.setIsPrimary(address.getIsPrimary());
        addressDTO.setIsDeleted(address.getIsDeleted());
        addressDTO.setUser(address.getUser() == null ? null : address.getUser().getId());
        return addressDTO;
    }

    private Address mapToEntity(final AddressDTO addressDTO, final Address address) {
        address.setLabel(addressDTO.getLabel());
        address.setDivision(addressDTO.getDivision());
        address.setDistrict(addressDTO.getDistrict());
        address.setUpazila(addressDTO.getUpazila());
        address.setLocalGovt(addressDTO.getLocalGovt());
        address.setAddressLine1(addressDTO.getAddressLine1());
        address.setAddressLine2(addressDTO.getAddressLine2());
        address.setLongitude(addressDTO.getLongitude());
        address.setLatitude(addressDTO.getLatitude());
        address.setIsPrimary(addressDTO.getIsPrimary());
        address.setIsDeleted(addressDTO.getIsDeleted());
        final Customer user = addressDTO.getUser() == null ? null : customerRepository.findById(addressDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        address.setUser(user);
        return address;
    }

    public boolean labelExists(final String label) {
        return addressRepository.existsByLabelIgnoreCase(label);
    }

}
