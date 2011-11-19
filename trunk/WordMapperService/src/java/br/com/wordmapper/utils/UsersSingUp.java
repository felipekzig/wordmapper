/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wordmapper.utils;

import br.com.wordmapper.service.container.UserContainer;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Felipe
 */
public class UsersSingUp {
    
    private final String URL = "jdbc:mysql://localhost/wordmapper";
    private final String USERNAME = "wordmapper";
    private final String PASSWORD = "wordmapper";
    
    private UserContainer user;
    
    public String responseJson;
    
    private Connection conec;
       
    public UsersSingUp(String jsonUser){
        try {
            this.conec = ConFactory.getConnection(URL, USERNAME, PASSWORD, ConFactory.MySQL);
            
            this.user = new Gson().fromJson(jsonUser, UserContainer.class);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean execute(){
        switch(this.user.getSqlOperation()){
            case UserContainer.INSERT:
                return this.insert();
                
            case UserContainer.UPDATE:
                return this.update();
        }
        
        return false;
    }
    
    private boolean insert(){
        
        String sql = "";
        
        sql = " INSERT INTO Users(";
        sql = sql + " FirstName, LastName, Email, City, Country, ZipCode, License";
        sql = sql + " ) VALUES (";
        sql = sql + this.user.getFirstName(true, true);
        sql = sql + this.user.getLastName(true, true);
        sql = sql + this.user.getEmail(true, true);
        sql = sql + this.user.getCity(true, true);
        sql = sql + this.user.getCountry(true, true);
        sql = sql + this.user.getZipCode(true, true);
        sql = sql + this.user.getLicense(true, false).toString();
        sql = sql + ")";
        
        try {
            Statement stmt = this.conec.createStatement();
            
            stmt.executeUpdate(sql);        
            stmt.close();
            
        } catch (SQLException e) {
            return false;
        }
        
        return true;
    }

    private boolean update(){
        
        String sql = "";
        
        sql = sql + " UPDATE Users SET";
        sql = sql + " FirstName = " + this.user.getFirstName(true, true);
        sql = sql + " LastName = " + this.user.getLastName(true, true);
        sql = sql + " Email = " + this.user.getEmail(true, true);
        sql = sql + " City = " + this.user.getCity(true, true);
        sql = sql + " Country = " + this.user.getCountry(true, true);
        sql = sql + " ZipCode = " + this.user.getZipCode(true, false);
        sql = sql + " WHERE Codigo = " + this.user.getId(true, false).toString();
      
        try {
            Statement stmt = this.conec.createStatement();
            
            stmt.executeUpdate(sql);        
            stmt.close();
            
        } catch (SQLException e) {
            return false;
        }
        
        return true;
    }
    
    public String getResponse(){
        return "";
    }
}

