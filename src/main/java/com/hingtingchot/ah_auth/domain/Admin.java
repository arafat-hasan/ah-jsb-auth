package com.hingtingchot.ah_auth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Admin extends User {

    @Column(nullable = false, unique = true)
    private String nid;

}
