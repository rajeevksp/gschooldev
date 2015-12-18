/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.db;

import org.javalite.activejdbc.Base;

/**
 *
 * @author Rajeev
 */
public class DbCon {

    public DbCon() {
        
        if(this.isConnected())
            Base.close();
        
        
         Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/goolschool", "root", "");
         
          
    }
    
    public boolean isConnected(){
       return Base.hasConnection();
    }
    
    public void closeDb(){
        Base.close();
    }
    
}
