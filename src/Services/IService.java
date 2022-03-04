/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
<<<<<<< HEAD
 * @author LENOVO
 */
public interface IService <Y>{
    void ajouter(Y y);
    List<Y> afficher();
    void modifier (Y y);
    void supprimer (Y y);
=======
 * @author DELL PRCISION 3551
 */
public interface IService<T> {
    void ajouter(T t);
    List<T> afficher();
    
>>>>>>> 8845519aac4386f17f05bd0a9b2fb7ace06bb4ce
}
