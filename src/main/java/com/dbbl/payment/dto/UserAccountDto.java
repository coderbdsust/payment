package com.dbbl.payment.dto;

import com.dbbl.payment.validator.NotEmptyOrNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserAccountDto {

    private Long id;

    private String username;

    @Email
    @NotBlank(message = "Email field is mandatory")
    private String email;

    @Size(min = 1, max = 16, message = "Password length in between 1 to 16  or not matched")
    private String password;

    @Size(min = 1, max = 16 , message = "Password length in between 1 to 16 or not matched")
    private String confirmPassword;

    @Size(min = 4, max = 30, message = "First name length will be in between 4 to 30 characters")
    private String firstName;

    @Size(min = 4, max = 30, message = "Last name length will be in between 4 to 30 characters")
    private String lastName;

    @NotBlank(message = "Contact is required")
    private String contact;

    @NotBlank(message = "Role is required")
    private String roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserAccountDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact='" + contact + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
