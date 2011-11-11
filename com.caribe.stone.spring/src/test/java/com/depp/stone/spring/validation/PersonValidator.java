package com.depp.stone.spring.validation;

import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.Errors;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.depp.stone.spring.validation.pojo.Person;

public class PersonValidator implements Validator {

	@Override
	public boolean supports(Class<?> clz) {
		// TODO Auto-generated method stub
		return Person.class.equals(clz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
		Person p = (Person) obj;
		if (p.getAge() < 0) {
			e.rejectValue("age", "negativevalue");
		} else if (p.getAge() > 110) {
			e.rejectValue("age", "too.darn.old");
		}
	}

	public static void main(String[] args) {
		MessageCodesResolver mr=new DefaultMessageCodesResolver();
	}
}
