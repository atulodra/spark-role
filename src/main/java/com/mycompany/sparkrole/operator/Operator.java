/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sparkrole.operator;

import com.mycompany.sparkrole.role.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

/**
 *
 * @author AVShrez
 */
public class Operator {

    private String name;

    @NotBlank(message = "Please enter email")
    @Email(regexp = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$", message = "Invalid email format")
    private String email;

    @Pattern(regexp
             = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$", message = "Password must contain at least 8 characters, one Uppercase character, one Lowercase character, a number and a special symbol"
    )
    private String password;

    private String status;

    @Max(value = 3)
    private int loginCount;
    private Role role;

    Operator() {
    }

    Operator(String name, String email, String status,
             int loginCount, Role role) {

        this.name = name;
        this.email = email;
        this.status = status;
        this.loginCount = loginCount;
        this.role = role;

    }

    Operator(String name, String email, String password, String status,
             int loginCount, Role role) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
        this.loginCount = loginCount;
        this.role = role;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
