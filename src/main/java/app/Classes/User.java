package app.Classes;

import app.Database.DB;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class User {
    private int user_id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String address;
    private LocalDate birthDate;
    private String Gender;
    private String profilePicture;

    public User(String name, String email, String password, String user, String address, String gender, LocalDate birthDate, String profilePicture) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.role = user;
        this.address = address;
        this.Gender = gender;
        this.birthDate = birthDate;
        this.profilePicture = profilePicture;
    }

    public User(int userId, String username, String email, String passwordHash, String role, String address, String gender, LocalDate birthDate, String profilePhoto) {
        this.user_id = userId;
        this.username = username;
        this.email = email;
        this.password = passwordHash;
        this.role = role;
        this.address = address;
        this.Gender = gender;
        this.birthDate = birthDate;
        this.profilePicture = profilePhoto;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public User() {
    }
    public User(int user_id, String username, String email, String password, String role, String address) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
    }

    public User(String id, String password) {
        this.user_id = Integer.parseInt(id);
        this.password = password;
    }

    public User(String name, String email, String password, String user, String address, String gender, LocalDate birthDate) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.role = user;
        this.address = address;
        this.Gender = gender;
        this.birthDate = birthDate;

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
