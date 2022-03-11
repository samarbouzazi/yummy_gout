/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author tchet
 */
public class ClientInfo {
    private int id_client;
    private String nom;
    private String prenom;
    private String adress;
    private String email;
    private Integer tel ;
    private Integer points;
    private LocalDate date;

    public ClientInfo() {
    }

    public ClientInfo(int id_client, String nom, String prenom, String adress, String email, int tel, int points, LocalDate date) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.email = email;
        this.tel = tel;
        this.points = points;
        this.date = date;
    }

    public ClientInfo(String nom, String prenom, String adress, String email, int tel, int points, LocalDate date) {
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.email = email;
        this.tel = tel;
        this.points = points;
        this.date = date;
    }

    public ClientInfo(int id_client, String nom, String prenom, String adress, String email, Integer tel, Integer points, LocalDate date) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.email = email;
        this.tel = tel;
        this.points = points;
        this.date = date;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    

    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientInfo other = (ClientInfo) obj;
        if (this.id_client != other.id_client) {
            return false;
        }
        if (this.tel != other.tel) {
            return false;
        }
        if (this.points != other.points) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClientInfo{" + "id_client=" + id_client + ", nom=" + nom + ", prenom=" + prenom + ", adress=" + adress + ", email=" + email + ", tel=" + tel + ", points=" + points + ", date=" + date + '}';
    }

    
    

    

    
    
}
