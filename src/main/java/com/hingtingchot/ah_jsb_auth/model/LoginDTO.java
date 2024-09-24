package com.hingtingchot.ah_jsb_auth.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotNull
    @Size(max = 255)
    private String email;
    
    @NotNull
    @Size(max = 255)
    private String password;
}
