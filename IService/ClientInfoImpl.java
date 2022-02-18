/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import java.util.List;

/**
 *
 * @author tchet
 * @param <T>
 */
public interface ClientInfoImpl <T> {
    void ajouter(T t);
    void delete(T t);
    void edit (T t);
    
    List<T> getAll();
}
