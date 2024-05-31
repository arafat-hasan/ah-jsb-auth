package com.hingtingchot.ah_auth.repos;

import com.hingtingchot.ah_auth.domain.SysAdmin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SysAdminRepository extends JpaRepository<SysAdmin, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhoneIgnoreCase(String phone);

    boolean existsByNidIgnoreCase(String nid);

}
