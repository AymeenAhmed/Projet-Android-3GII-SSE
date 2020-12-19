package com.example.admin;

public class Empl {
    private String name , password , state ;

    public Empl(String name, String password, String state) {
        this.name = name;
        this.password = password;
        this.state = state;
    }

    public Empl() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
