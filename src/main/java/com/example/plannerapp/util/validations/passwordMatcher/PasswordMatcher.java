package com.example.plannerapp.util.validations.passwordMatcher;


import com.example.plannerapp.model.binding.UserRegisterModel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegisterModel> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterModel userRegisterModel, ConstraintValidatorContext context) {

        return userRegisterModel.getPassword() != null && userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword());

    }
}
