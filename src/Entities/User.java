/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entités;

/**
 *
 * @author DELL PRCISION 3551
 */
public class User {
    private int idu;
    private String nomu;
    private String prenomu;
    private int cinu;
    private int telu;
    private String emailu;
    private String password;
    private String role;

    public User() {
    }

    public User(int idu, String nomu, String prenomu, int cinu, int telu, String emailu, String password, String role) {
        this.idu = idu;
        this.nomu = nomu;
        this.prenomu = prenomu;
        this.cinu = cinu;
        this.telu = telu;
        this.emailu = emailu;
        this.password = password;
        this.role = role;
    }

    public User(String nomu, String prenomu, int cinu, int telu, String emailu, String password, String role) {
        this.nomu = nomu;
        this.prenomu = prenomu;
        this.cinu = cinu;
        this.telu = telu;
        this.emailu = emailu;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getNomu() {
        return nomu;
    }

    public void setNomu(String nomu) {
        this.nomu = nomu;
    }

    public String getPrenomu() {
        return prenomu;
    }

    public void setPrenomu(String prenomu) {
        this.prenomu = prenomu;
    }

    public int getCinu() {
        return cinu;
    }

    public void setCinu(int cinu) {
        this.cinu = cinu;
    }

    public int getTelu() {
        return telu;
    }

    public void setTelu(int telu) {
        this.telu = telu;
    }

    public String getEmailu() {
        return emailu;
    }

    public void setEmailu(String emailu) {
        this.emailu = emailu;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "idu=" + idu + ", nomu=" + nomu + ", prenomu=" + prenomu + ", cinu=" + cinu + ", telu=" + telu + ", emailu=" + emailu + ", role=" + role + '}';
    }

    
}
