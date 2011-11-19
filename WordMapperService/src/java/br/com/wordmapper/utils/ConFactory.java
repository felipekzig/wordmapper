/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wordmapper.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Felipe
 */
public class ConFactory {

    public static final int MySQL = 0;
    private static final String MySqlDriver = "com.mysql.jdbc.Driver";
    
    public static Connection getConnection(String url, String username, String password, int database) throws ClassNotFoundException, SQLException{
        switch(database){
            case MySQL:
                Class.forName(MySqlDriver);
            break;
        }
        
        return DriverManager.getConnection(url, username, password);
    }
    
}
