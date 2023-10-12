/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkrole.utils.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author AVShrez
 */
public class PasswordValidator {

    private final static Pattern passwordPattern = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$");

    public static Boolean isPasswordValid(String email) {
        Matcher matcher = passwordPattern.matcher(email);
        return matcher.matches();
    }
}
