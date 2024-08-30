package com.hingtingchot.ah_jsb_auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    @AddressLabelUnique
    private String label;

    @NotNull
    @Size(max = 255)
    private String division;

    @NotNull
    @Size(max = 255)
    private String district;

    @NotNull
    @Size(max = 255)
    private String upazila;

    @NotNull
    @Size(max = 255)
    private String localGovt;

    @NotNull
    @Size(max = 255)
    private String addressLine1;

    @Size(max = 255)
    private String addressLine2;

    @Size(max = 255)
    private String longitude;

    @Size(max = 255)
    private String latitude;

    @JsonProperty("isPrimary")
    private Boolean isPrimary;

    @JsonProperty("isDeleted")
    private Boolean isDeleted;

    private Long user;

}
