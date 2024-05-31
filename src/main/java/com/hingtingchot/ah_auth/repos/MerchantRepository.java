package com.hingtingchot.ah_auth.repos;

import com.hingtingchot.ah_auth.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneIgnoreCase(String phone);

    boolean existsByNidIgnoreCase(String nid);

}
