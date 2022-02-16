/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author DELL PRCISION 3551
 */
public class Stock {
    private int ids;
    private String noms;
    private String date_ajout_s;
    private String date_fin_s;
    private float prix_s;
    private int qt_s;

    public Stock() {
    }

    public Stock(int ids, String noms, String date_ajout_s, String date_fin_s, float prix_s, int qt_s) {
        this.ids = ids;
        this.noms = noms;
        this.date_ajout_s = date_ajout_s;
        this.date_fin_s = date_fin_s;
        this.prix_s = prix_s;
        this.qt_s = qt_s;
    }

    public Stock(String noms, String date_ajout_s, String date_fin_s, float prix_s, int qt_s) {
        this.noms = noms;
        this.date_ajout_s = date_ajout_s;
        this.date_fin_s = date_fin_s;
        this.prix_s = prix_s;
        this.qt_s = qt_s;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getNoms() {
        return noms;
    }

    public void setNoms(String noms) {
        this.noms = noms;
    }

    public String getDate_ajout_s() {
        return date_ajout_s;
    }

    public void setDate_ajout_s(String date_ajout_s) {
        this.date_ajout_s = date_ajout_s;
    }

    public String getDate_fin_s() {
        return date_fin_s;
    }

    public void setDate_fin_s(String date_fin_s) {
        this.date_fin_s = date_fin_s;
    }

    public float getPrix_s() {
        return prix_s;
    }

    public void setPrix_s(float prix_s) {
        this.prix_s = prix_s;
    }

    public int getQt_s() {
        return qt_s;
    }

    public void setQt_s(int qt_s) {
        this.qt_s = qt_s;
    }

    @Override
    public String toString() {
        return "Stock{" + "ids=" + ids + ", noms=" + noms + ", date_ajout_s=" + date_ajout_s + ", date_fin_s=" + date_fin_s + ", prix_s=" + prix_s + ", qt_s=" + qt_s + '}';
    }

}