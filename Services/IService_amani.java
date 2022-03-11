/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface IService_amani <Y>{
    void ajouter(Y y);
    List<Y> afficher();
    void modifier (Y y);
    void supprimer (Y y);
}
