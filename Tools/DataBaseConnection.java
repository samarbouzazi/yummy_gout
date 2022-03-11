/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author tchet
 */
public class DataBaseConnection {
        private Connection cn ;

    private String url ="jdbc:mysql://localhost:3306/yummyjava";
    public String username ="root";
    public  String pwd="";
    
    // First Step For the method singelton
    private static DataBaseConnection instance;
    // Second step
    private DataBaseConnection() {
        try {
             cn = DriverManager.getConnection(url, username, pwd);
             System.out.println("Connection Succeeded");
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
    }
    public static DataBaseConnection getInstance(){
        if(instance==null)
            instance = new DataBaseConnection();
        return instance;
   }

    public Connection getCn() {
        return cn;
    }
    
}
