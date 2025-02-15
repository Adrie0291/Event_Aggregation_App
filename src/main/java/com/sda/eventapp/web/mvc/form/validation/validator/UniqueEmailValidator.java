package com.sda.eventapp.web.mvc.form.validation.validator;

import com.sda.eventapp.service.UserService;
import com.sda.eventapp.web.mvc.form.CreateUserForm;
import com.sda.eventapp.web.mvc.form.validation.constraint.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, CreateUserForm> {
    private final UserService userService;

    @Override
    public void initialize(UniqueEmail constraint) {
    }

    @Override
    public boolean isValid(CreateUserForm form, ConstraintValidatorContext context) {
        return !userService.existsByEmail(form.getEmail());
    }
}
