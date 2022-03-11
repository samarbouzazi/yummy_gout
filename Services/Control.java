/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.ClientInfo;
import Entities.Reservation;
import Tools.DataBaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tchet
 */
public class Control {
     public int existe(ClientInfo c) throws SQLException {
         Connection connection = DataBaseConnection.getInstance().getCn();

        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from clientinfo WHERE id_client = '" + c.getId_client()+"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
    }
     public int existe(Reservation r) throws SQLException {
         Connection connection = DataBaseConnection.getInstance().getCn();

        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery("SELECT COUNT(*) from reservation WHERE resId = '" + r.getResId()+"'");
        int size = 0;
        rs.next();
        size=rs.getInt(1);
        return size;
    }
}
