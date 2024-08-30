package com.hingtingchot.ah_jsb_auth.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hingtingchot.ah_jsb_auth.domain.SysAdmin;
import com.hingtingchot.ah_jsb_auth.model.SysAdminDTO;
import com.hingtingchot.ah_jsb_auth.repos.SysAdminRepository;
import com.hingtingchot.ah_jsb_auth.util.NotFoundException;


@Service
public class SysAdminService {

    private final SysAdminRepository sysAdminRepository;

    public SysAdminService(final SysAdminRepository sysAdminRepository) {
        this.sysAdminRepository = sysAdminRepository;
    }

    public List<SysAdminDTO> findAll() {
        final List<SysAdmin> sysAdmins = sysAdminRepository.findAll(Sort.by("id"));
        return sysAdmins.stream()
                .map(sysAdmin -> mapToDTO(sysAdmin, new SysAdminDTO()))
                .toList();
    }

    public SysAdminDTO get(final Long id) {
        return sysAdminRepository.findById(id)
                .map(sysAdmin -> mapToDTO(sysAdmin, new SysAdminDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final SysAdminDTO sysAdminDTO) {
        final SysAdmin sysAdmin = new SysAdmin();
        mapToEntity(sysAdminDTO, sysAdmin);
        return sysAdminRepository.save(sysAdmin).getId();
    }

    public void update(final Long id, final SysAdminDTO sysAdminDTO) {
        final SysAdmin sysAdmin = sysAdminRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(sysAdminDTO, sysAdmin);
        sysAdminRepository.save(sysAdmin);
    }

    public void delete(final Long id) {
        sysAdminRepository.deleteById(id);
    }

    private SysAdminDTO mapToDTO(final SysAdmin sysAdmin, final SysAdminDTO sysAdminDTO) {
        sysAdminDTO.setId(sysAdmin.getId());
        sysAdminDTO.setFirstName(sysAdmin.getFirstName());
        sysAdminDTO.setLastName(sysAdmin.getLastName());
        sysAdminDTO.setGender(sysAdmin.getGender());
        sysAdminDTO.setDob(sysAdmin.getDob());
        sysAdminDTO.setEmail(sysAdmin.getEmail());
        sysAdminDTO.setPhone(sysAdmin.getPhone());
        sysAdminDTO.setSecondaryPhone(sysAdmin.getSecondaryPhone());
        sysAdminDTO.setNid(sysAdmin.getNid());
        sysAdminDTO.setOccupation(sysAdmin.getOccupation());
        sysAdminDTO.setOrganization(sysAdmin.getOrganization());
        sysAdminDTO.setProfilePicURL(sysAdmin.getProfilePicURL());
        sysAdminDTO.setPassword(sysAdmin.getPassword());
        sysAdminDTO.setLastResetAt(sysAdmin.getLastResetAt());
        sysAdminDTO.setCreatedAt(sysAdmin.getCreatedAt());
        sysAdminDTO.setStatus(sysAdmin.getStatus());
        sysAdminDTO.setUpdatedAt(sysAdmin.getUpdatedAt());
        sysAdminDTO.setUpdatedBy(sysAdmin.getUpdatedBy());
        sysAdminDTO.setNid(sysAdmin.getNid());
        return sysAdminDTO;
    }

    private SysAdmin mapToEntity(final SysAdminDTO sysAdminDTO, final SysAdmin sysAdmin) {
        sysAdmin.setFirstName(sysAdminDTO.getFirstName());
        sysAdmin.setLastName(sysAdminDTO.getLastName());
        sysAdmin.setGender(sysAdminDTO.getGender());
        sysAdmin.setDob(sysAdminDTO.getDob());
        sysAdmin.setEmail(sysAdminDTO.getEmail());
        sysAdmin.setPhone(sysAdminDTO.getPhone());
        sysAdmin.setSecondaryPhone(sysAdminDTO.getSecondaryPhone());
        sysAdmin.setNid(sysAdminDTO.getNid());
        sysAdmin.setOccupation(sysAdminDTO.getOccupation());
        sysAdmin.setOrganization(sysAdminDTO.getOrganization());
        sysAdmin.setProfilePicURL(sysAdminDTO.getProfilePicURL());
        sysAdmin.setPassword(sysAdminDTO.getPassword());
        sysAdmin.setLastResetAt(sysAdminDTO.getLastResetAt());
        sysAdmin.setCreatedAt(sysAdminDTO.getCreatedAt());
        sysAdmin.setStatus(sysAdminDTO.getStatus());
        sysAdmin.setUpdatedAt(sysAdminDTO.getUpdatedAt());
        sysAdmin.setUpdatedBy(sysAdminDTO.getUpdatedBy());
        sysAdmin.setNid(sysAdminDTO.getNid());
        return sysAdmin;
    }

    public boolean emailExists(final String email) {
        return sysAdminRepository.existsByEmailIgnoreCase(email);
    }

    public boolean phoneExists(final String phone) {
        return sysAdminRepository.existsByPhoneIgnoreCase(phone);
    }

    public boolean nidExists(final String nid) {
        return sysAdminRepository.existsByNidIgnoreCase(nid);
    }

}
