/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author DELL PRCISION 3551
 */
public interface IService<T> {
    void ajouter(T t);
    List<T> afficher();
    
    
}
