/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkrole.utils.validators;

import com.mycompany.sparkrole.operator.Operator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author AVShrez
 */
public class OperatorValidator {

    private static final ValidatorFactory factory = Validation
            .buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static List<String> validate(Operator operator) {
        List<String> violationMessages = new ArrayList<>();
        Set<ConstraintViolation<Operator>> constraintViolations = validator
                .validate(operator);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<Operator> violation : constraintViolations) {
                violationMessages.add(violation
                        .getMessage());
            }
            return violationMessages;
        }
        violationMessages.add("Ok");
        return violationMessages;
    }

}
