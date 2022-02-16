/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entités;

/**
 *
 * @author HP
 */
public class chefemp extends personnel {
    private int idchef ;

    public chefemp(int idchef, String nomp, String prenom, String mail, int tel, int salaire, String Specialite) {
        super(nomp, prenom, mail, tel, salaire, Specialite);
        this.idchef = idchef;
    }

    

    
   
    public int getIdchef() {
        return idchef;
    }

    public void setIdchef(int idchef) {
        this.idchef = idchef;
    }

    @Override
    public String toString() {
        return "chefemp{" + "idchef=" + idchef + '}';
    }
    
}
