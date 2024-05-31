package com.hingtingchot.ah_auth.service;

import com.hingtingchot.ah_auth.domain.Admin;
import com.hingtingchot.ah_auth.model.AdminDTO;
import com.hingtingchot.ah_auth.repos.AdminRepository;
import com.hingtingchot.ah_auth.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(final AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<AdminDTO> findAll() {
        final List<Admin> admins = adminRepository.findAll(Sort.by("id"));
        return admins.stream()
                .map(admin -> mapToDTO(admin, new AdminDTO()))
                .toList();
    }

    public AdminDTO get(final Long id) {
        return adminRepository.findById(id)
                .map(admin -> mapToDTO(admin, new AdminDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final AdminDTO adminDTO) {
        final Admin admin = new Admin();
        mapToEntity(adminDTO, admin);
        return adminRepository.save(admin).getId();
    }

    public void update(final Long id, final AdminDTO adminDTO) {
        final Admin admin = adminRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(adminDTO, admin);
        adminRepository.save(admin);
    }

    public void delete(final Long id) {
        adminRepository.deleteById(id);
    }

    private AdminDTO mapToDTO(final Admin admin, final AdminDTO adminDTO) {
        adminDTO.setId(admin.getId());
        adminDTO.setFirstName(admin.getFirstName());
        adminDTO.setLastName(admin.getLastName());
        adminDTO.setGender(admin.getGender());
        adminDTO.setDob(admin.getDob());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setPhone(admin.getPhone());
        adminDTO.setSecondaryPhone(admin.getSecondaryPhone());
        adminDTO.setNid(admin.getNid());
        adminDTO.setOccupation(admin.getOccupation());
        adminDTO.setOrganization(admin.getOrganization());
        adminDTO.setProfilePicURL(admin.getProfilePicURL());
        adminDTO.setPassword(admin.getPassword());
        adminDTO.setLastResetAt(admin.getLastResetAt());
        adminDTO.setCreatedAt(admin.getCreatedAt());
        adminDTO.setStatus(admin.getStatus());
        adminDTO.setUpdatedAt(admin.getUpdatedAt());
        adminDTO.setUpdatedBy(admin.getUpdatedBy());
        adminDTO.setNid(admin.getNid());
        return adminDTO;
    }

    private Admin mapToEntity(final AdminDTO adminDTO, final Admin admin) {
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setGender(adminDTO.getGender());
        admin.setDob(adminDTO.getDob());
        admin.setEmail(adminDTO.getEmail());
        admin.setPhone(adminDTO.getPhone());
        admin.setSecondaryPhone(adminDTO.getSecondaryPhone());
        admin.setNid(adminDTO.getNid());
        admin.setOccupation(adminDTO.getOccupation());
        admin.setOrganization(adminDTO.getOrganization());
        admin.setProfilePicURL(adminDTO.getProfilePicURL());
        admin.setPassword(adminDTO.getPassword());
        admin.setLastResetAt(adminDTO.getLastResetAt());
        admin.setCreatedAt(adminDTO.getCreatedAt());
        admin.setStatus(adminDTO.getStatus());
        admin.setUpdatedAt(adminDTO.getUpdatedAt());
        admin.setUpdatedBy(adminDTO.getUpdatedBy());
        admin.setNid(adminDTO.getNid());
        return admin;
    }

    public boolean emailExists(final String email) {
        return adminRepository.existsByEmailIgnoreCase(email);
    }

    public boolean phoneExists(final String phone) {
        return adminRepository.existsByPhoneIgnoreCase(phone);
    }

    public boolean nidExists(final String nid) {
        return adminRepository.existsByNidIgnoreCase(nid);
    }

}
