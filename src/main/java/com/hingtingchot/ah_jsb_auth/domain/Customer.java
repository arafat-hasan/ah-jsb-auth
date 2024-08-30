package com.hingtingchot.ah_jsb_auth.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Customer extends User {

    @OneToMany(mappedBy = "user")
    private Set<Address> addresses;

}
