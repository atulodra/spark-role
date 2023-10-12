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
public class EmailValidator {

    private final static Pattern emailPattern = Pattern.compile(
            "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");

    public static Boolean isEmailValid(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
}
