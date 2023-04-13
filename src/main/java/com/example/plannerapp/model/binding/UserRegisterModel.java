package com.example.plannerapp.model.binding;

import com.example.plannerapp.util.validations.checkUsernameAndEmailExistence.ValidateUniqueFields;
import com.example.plannerapp.util.validations.passwordMatcher.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@PasswordMatch(message = "Passwords don't match!")
@ValidateUniqueFields
public class UserRegisterModel {

    @Size(min = 3, max = 20)
    @NotNull
    private String username;

    @Size(min = 3, max = 20)
    @NotNull
    private String password;

    @Size(min = 3, max = 20)
    @NotNull
    private String confirmPassword;

    @Email
    @NotEmpty
    private String email;
}
