package com.epam.jt.name.entity;

public class User extends Entity{
    private String username;
    private String mail;
    private long id;
    private String password;
    private String role;
    private double fine;

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    private boolean isBanned;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public User(String username, String mail, long id, String password, String role, double fine) {
        this.username = username;
        this.mail = mail;
        this.id = id;
        this.password = password;
        this.role = role;
        this.fine = fine;
    }
    public User(){
        //hello
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                ", id=" + id +
                ", role='" + role + '\'' +
                ", fine=" + fine +
                ", isBanned=" + isBanned +
                '}';
    }
}
