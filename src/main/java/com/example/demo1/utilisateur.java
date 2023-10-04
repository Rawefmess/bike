package com.example.demo1;

public class utilisateur {
    private int id ;
    private String username;
    private String email;
    private String password;
    private String role;

    private String gender;

    private String image;

    private String date;


    public utilisateur() {
    }

    public utilisateur(int id, String username, String email, String password, String role, String gender, String image, String date) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.gender = gender;
        this.image = image;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getGender() {
        return gender;
    }

    public String getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }
}
