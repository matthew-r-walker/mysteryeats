package com.mysteryeats.mysteryeats.models;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 25, unique = true)
    @Pattern(regexp = "^[a-zA-Z]\\w{4,24}", message = "Usernames must be 5-25 chars long, start with a letter and may only contain A-z, 0-9 and underscore.")
    private String username;

    @Column(nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 8, max = 100, message = "Password must be at least 8 chars in length")
    private String password;

    @Column(nullable = false, length = 20)
    @NotBlank
    @Size(max = 20, message = "must not be longer than 20 characters")
    private String firstName;

    @Column(nullable = false, length = 30)
    @NotBlank
    @Size(max = 30, message = "must not be longer than 30 characters")
    private String lastName;

    public User() {
    }

    public User(long id, String username, String email, String password, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
