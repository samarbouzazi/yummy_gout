/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author DELL PRCISION 3551
 */
public class User {

    private int id;
    private String email;
    private String roles;
    private String password;
    private int cin;
    private String username;
    private String activation_token;
    private String reset_token;

    public User() {
    }

    public User(int id, String email, String roles, String password, int cin, String username) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.cin = cin;
        this.username = username;
    }

    public User(String email, String roles, String password, int cin, String username) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.cin = cin;
        this.username = username;
    }

  

    public int getId() {
        return id;
    }

    public User(int id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
  
        return password= password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", roles=" + roles + ", password=" + password + ", cin=" + cin + ", username=" + username + ", activation_token=" + activation_token + ", reset_token=" + reset_token + '}';
    }

    
    
}
