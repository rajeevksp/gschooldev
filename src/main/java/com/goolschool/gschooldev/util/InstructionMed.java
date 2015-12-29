/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.util;

import com.goolschool.gschooldev.db.DbCon;
import entity.InstructionMedium;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Rajeev
 */


@Named
@RequestScoped
public class InstructionMed {
    
    private String medium;
    private int uid;
  
    DbCon db;

    
    

    public List<InstructionMed> mediumList() {
          List<InstructionMed> medium_list =  new ArrayList<>();
         db = new DbCon();
         
          List<InstructionMedium> data_d = InstructionMedium.findAll();
          
          if(data_d.size() > 0){
              
             
              for(InstructionMedium med: data_d){
             System.out.print("ins code:"+med.getInteger("code")+" - "+med.getString("language"));
             InstructionMed insmed = new InstructionMed();
             insmed.setUid(med.getInteger("code"));
             insmed.setMedium(med.getString("language"));
             medium_list.add(insmed);
             
         } 
          }
          
        
        return medium_list;
    
    }
    
    

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

     

    
    
    
}
