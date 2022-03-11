/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author tchet
 */
public class Reservation {
    
    private int resId;
    private String notes;
    private String view;
    private int guestNum;
    private LocalDate resDate;
    private String eventType;
    private int branchId ;
    private String branchLocation;

//    public Reservation(int resId, String notes, String view, int guestNum, LocalDate resDate, String eventType, int branchId, String branchLocation) {
//        this.resId = resId;
//        this.notes = notes;
//        this.view = view;
//        this.guestNum = guestNum;
//        this.resDate = resDate;
//        this.eventType = eventType;
//        this.branchId = branchId;
//        this.branchLocation = branchLocation;
//    }

    public Reservation(int resId, String notes, String view, int guestNum, LocalDate resDate, String branchLocation) {
        this.resId = resId;
        this.notes = notes;
        this.view = view;
        this.guestNum = guestNum;
        this.resDate = resDate;
        this.branchLocation = branchLocation;
    }

    public Reservation() {
    }

//    public Reservation(String notes, String view, int guestNum, LocalDate resDate, String eventType, int branchId, String branchLocation) {
//        this.notes = notes;
//        this.view = view;
//        this.guestNum = guestNum;
//        this.resDate = resDate;
//        this.eventType = eventType;
//        this.branchId = branchId;
//        this.branchLocation = branchLocation;
//    }

    public Reservation(String notes, String view, int guestNum, LocalDate resDate, String eventType, String branchLocation) {
        this.notes = notes;
        this.view = view;
        this.guestNum = guestNum;
        this.resDate = resDate;
        this.eventType = eventType;
        this.branchLocation = branchLocation;
    }

    public Reservation(int resId, String notes, String view, int guestNum, LocalDate resDate, String eventType, String branchLocation) {
 this.notes = notes;
        this.view = view;
        this.guestNum = guestNum;
        this.resDate = resDate;
        this.eventType = eventType;
        this.branchLocation = branchLocation;   
    }

    public Reservation(String notes, String view, String eventType, String branchLocation, LocalDate resDate, int guestNum) {
            this.notes = notes;
        this.view = view;
        this.eventType = eventType;
        this.branchLocation = branchLocation;
        this.resDate = resDate;
        this.guestNum = guestNum;     }

    public Reservation(String note, String viewName, String guest_number, LocalDate date, String evenType, String branchLocation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public int getGuestNum() {
        return guestNum;
    }

    public void setGuestNum(int guestNum) {
        this.guestNum = guestNum;
    }

    public LocalDate getResDate() {
        return resDate;
    }

    public void setResDate(LocalDate resDate) {
        this.resDate = resDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

//    public int getBranchId() {
//        return branchId;
//    }
//
//    public void setBranchId(int branchId) {
//        this.branchId = branchId;
//    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public void setBranchLocation(String branchLocation) {
        this.branchLocation = branchLocation;
    }

   

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Reservation other = (Reservation) obj;
        if (this.resId != other.resId) {
            return false;
        }
        if (this.guestNum != other.guestNum) {
            return false;
        }
//        if (this.branchId != other.branchId) {
//            return false;
//        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        if (!Objects.equals(this.view, other.view)) {
            return false;
        }
        if (!Objects.equals(this.eventType, other.eventType)) {
            return false;
        }
        if (!Objects.equals(this.branchLocation, other.branchLocation)) {
            return false;
        }
        if (!Objects.equals(this.resDate, other.resDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "resId=" + resId + ", notes=" + notes + ", view=" + view + ", guestNum=" + guestNum + ", resDate=" + resDate + ", eventType=" + eventType +", branchLocation=" + branchLocation + '}';
    }

    
    }

    
    
        
