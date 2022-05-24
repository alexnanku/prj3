/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prj3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexioBD {
private static Connection connexioBD = null;

    public void connexio() {

        String servidor = "jdbc:mysql://localhost:3306/";
        String usuari = "root";
        String passwd = "";
        String bbdd = "esqui";

        try {
            connexioBD = DriverManager.getConnection(servidor + bbdd, usuari, passwd);
            System.out.println("Connexió establida amb èxit");

        } catch (SQLException e) {
            System.out.println("Ha fallat la connexió.");
            e.printStackTrace();
        }
    }

    public static Connection getConnexioBD() {
        return connexioBD;
    }

    public void tancarConnexioBD() throws SQLException {
        connexioBD.close();
    }
    
}
