/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.time.LocalDate;

/**
 *
 * @author tchet
 */
public class Reservation {
    
    private int id_reservation;
    private String description;
    private String emplacement;
    private int nb_personnes;
    private LocalDate date_res;
    private String type_event;
    private Boolean disponible;
        private int points;

    private int id_branche ;

    public Reservation() {
    }

    public Reservation(int id_reservation, String description, String emplacement, int nb_personnes, LocalDate date_res, String type_event, Boolean disponible, int points, int id_branche) {
        this.id_reservation = id_reservation;
        this.description = description;
        this.emplacement = emplacement;
        this.nb_personnes = nb_personnes;
        this.date_res = date_res;
        this.type_event = type_event;
        this.disponible = disponible;
        this.points = points;
        this.id_branche = id_branche;
    }

    public Reservation(String description, String emplacement, int nb_personnes, LocalDate date_res, String type_event, Boolean disponible, int points, int id_branche) {
        this.description = description;
        this.emplacement = emplacement;
        this.nb_personnes = nb_personnes;
        this.date_res = date_res;
        this.type_event = type_event;
        this.disponible = disponible;
        this.points = points;
        this.id_branche = id_branche;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public int getNb_personnes() {
        return nb_personnes;
    }

    public void setNb_personnes(int nb_personnes) {
        this.nb_personnes = nb_personnes;
    }

    public LocalDate getDate_res() {
        return date_res;
    }

    public void setDate_res(LocalDate date_res) {
        this.date_res = date_res;
    }

    public String getType_event() {
        return type_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getId_branche() {
        return id_branche;
    }

    public void setId_branche(int id_branche) {
        this.id_branche = id_branche;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", description=" + description + ", emplacement=" + emplacement + ", nb_personnes=" + nb_personnes + ", date_res=" + date_res + ", type_event=" + type_event + ", disponible=" + disponible + ", points=" + points + ", id_branche=" + id_branche + '}';
    }

   

  

   
 


   
        
    
}
