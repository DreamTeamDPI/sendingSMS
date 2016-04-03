package com.common.user;

/**
 * Created by SemmEs on 03.04.2016.
 */
public class User {

    private String login;
    private String password;
    private double credit;

    public User() {
    }

    public User(String login, String password, double credit) {
        this.login = login;
        this.password = password;
        this.credit = credit;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", credit='" + credit + '\'' +
                '}';
    }
}
