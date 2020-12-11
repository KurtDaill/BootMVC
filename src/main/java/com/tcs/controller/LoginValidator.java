package com.tcs.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tcs.model.Login;

@Component
@Profile("PRODUCTION")
public class LoginValidator implements Validator {
	@Override
	public boolean supports(Class<?> paramClass) {
		return Login.class.equals(paramClass);
	}
	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "required.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password");
	}
}
