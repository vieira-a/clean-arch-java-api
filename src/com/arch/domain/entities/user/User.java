package com.arch.domain.entities.user;

import com.arch.utils.ValidationUtil;

public class User {
    private String firstname;
    private String lastname;
    private String completeName;
    private String email;

    public User(String firstname, String lastname, String email) {
        this.firstname = ValidationUtil.validateEmptyField(firstname, "firstname");
        this.lastname = ValidationUtil.validateEmptyField(lastname, "lastname");
        this.completeName = firstname + " " + lastname;
        this.email = ValidationUtil.validateEmptyField(email, "email");
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCompleteName() {
        return completeName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}


