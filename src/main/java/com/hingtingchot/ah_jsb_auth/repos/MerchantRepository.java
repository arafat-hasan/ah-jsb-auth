package com.hingtingchot.ah_jsb_auth.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hingtingchot.ah_jsb_auth.domain.Merchant;


public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneIgnoreCase(String phone);

    boolean existsByNidIgnoreCase(String nid);

}
