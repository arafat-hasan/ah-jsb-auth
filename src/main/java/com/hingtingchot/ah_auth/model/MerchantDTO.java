package com.hingtingchot.ah_auth.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MerchantDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    @NotNull
    @Size(max = 255)
    private String gender;

    @NotNull
    private LocalDate dob;

    @NotNull
    @Size(max = 255)
    @MerchantEmailUnique
    private String email;

    @NotNull
    @Size(max = 255)
    @MerchantPhoneUnique
    private String phone;

    @Size(max = 255)
    private String secondaryPhone;

    @NotNull
    @Size(max = 255)
    @MerchantNidUnique
    private String nid;

    @Size(max = 255)
    private String occupation;

    @Size(max = 255)
    private String organization;

    @Size(max = 255)
    private String profilePicURL;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    private OffsetDateTime lastResetAt;

    @NotNull
    private OffsetDateTime createdAt;

    @NotNull
    private Boolean status;

    @NotNull
    private OffsetDateTime updatedAt;

    @Size(max = 255)
    private String updatedBy;
}
