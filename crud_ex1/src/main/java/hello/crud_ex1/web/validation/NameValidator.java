package hello.crud_ex1.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NameValidator implements ConstraintValidator<Name, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		log.info("namevalidator = {}",value);
		if(!StringUtils.hasText(value)) return false;
		return value.matches("^[가-힣Ra-zA-Z]*$");
	}
}
