package com.hingtingchot.ah_auth.service;

import com.hingtingchot.ah_auth.domain.Merchant;
import com.hingtingchot.ah_auth.model.MerchantDTO;
import com.hingtingchot.ah_auth.repos.MerchantRepository;
import com.hingtingchot.ah_auth.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(final MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public List<MerchantDTO> findAll() {
        final List<Merchant> merchants = merchantRepository.findAll(Sort.by("id"));
        return merchants.stream()
                .map(merchant -> mapToDTO(merchant, new MerchantDTO()))
                .toList();
    }

    public MerchantDTO get(final Long id) {
        return merchantRepository.findById(id)
                .map(merchant -> mapToDTO(merchant, new MerchantDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final MerchantDTO merchantDTO) {
        final Merchant merchant = new Merchant();
        mapToEntity(merchantDTO, merchant);
        return merchantRepository.save(merchant).getId();
    }

    public void update(final Long id, final MerchantDTO merchantDTO) {
        final Merchant merchant = merchantRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(merchantDTO, merchant);
        merchantRepository.save(merchant);
    }

    public void delete(final Long id) {
        merchantRepository.deleteById(id);
    }

    private MerchantDTO mapToDTO(final Merchant merchant, final MerchantDTO merchantDTO) {
        merchantDTO.setId(merchant.getId());
        merchantDTO.setFirstName(merchant.getFirstName());
        merchantDTO.setLastName(merchant.getLastName());
        merchantDTO.setGender(merchant.getGender());
        merchantDTO.setDob(merchant.getDob());
        merchantDTO.setEmail(merchant.getEmail());
        merchantDTO.setPhone(merchant.getPhone());
        merchantDTO.setSecondaryPhone(merchant.getSecondaryPhone());
        merchantDTO.setNid(merchant.getNid());
        merchantDTO.setOccupation(merchant.getOccupation());
        merchantDTO.setOrganization(merchant.getOrganization());
        merchantDTO.setProfilePicURL(merchant.getProfilePicURL());
        merchantDTO.setPassword(merchant.getPassword());
        merchantDTO.setLastResetAt(merchant.getLastResetAt());
        merchantDTO.setCreatedAt(merchant.getCreatedAt());
        merchantDTO.setStatus(merchant.getStatus());
        merchantDTO.setUpdatedAt(merchant.getUpdatedAt());
        merchantDTO.setUpdatedBy(merchant.getUpdatedBy());
        merchantDTO.setNid(merchant.getNid());
        return merchantDTO;
    }

    private Merchant mapToEntity(final MerchantDTO merchantDTO, final Merchant merchant) {
        merchant.setFirstName(merchantDTO.getFirstName());
        merchant.setLastName(merchantDTO.getLastName());
        merchant.setGender(merchantDTO.getGender());
        merchant.setDob(merchantDTO.getDob());
        merchant.setEmail(merchantDTO.getEmail());
        merchant.setPhone(merchantDTO.getPhone());
        merchant.setSecondaryPhone(merchantDTO.getSecondaryPhone());
        merchant.setNid(merchantDTO.getNid());
        merchant.setOccupation(merchantDTO.getOccupation());
        merchant.setOrganization(merchantDTO.getOrganization());
        merchant.setProfilePicURL(merchantDTO.getProfilePicURL());
        merchant.setPassword(merchantDTO.getPassword());
        merchant.setLastResetAt(merchantDTO.getLastResetAt());
        merchant.setCreatedAt(merchantDTO.getCreatedAt());
        merchant.setStatus(merchantDTO.getStatus());
        merchant.setUpdatedAt(merchantDTO.getUpdatedAt());
        merchant.setUpdatedBy(merchantDTO.getUpdatedBy());
        merchant.setNid(merchantDTO.getNid());
        return merchant;
    }

    public boolean emailExists(final String email) {
        return merchantRepository.existsByEmailIgnoreCase(email);
    }

    public boolean phoneExists(final String phone) {
        return merchantRepository.existsByPhoneIgnoreCase(phone);
    }

    public boolean nidExists(final String nid) {
        return merchantRepository.existsByNidIgnoreCase(nid);
    }

}
