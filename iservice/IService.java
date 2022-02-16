/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;

import java.util.List;

/**
 *
 * @author rezki
 */
public interface IService<T>{
    void ajouter(T t);
    List<T> afficher();
    void modifier(T t);
    void supprimer (T t);
    
    
}

    
