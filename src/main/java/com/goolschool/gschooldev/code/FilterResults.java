/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.code;

import com.goolschool.gschooldev.util.apiClass;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
  
  
//import javax.inject.Named;
/**
 *
 * @author Rajeev
 */
@ManagedBean(name = "filterResults")
@SessionScoped
public class FilterResults implements Serializable{
    
    private String as_values_location;
        private String standard;
    private int medium;
    private String fee_yealry;
    private String board;
    private int school_type;
    private boolean transportation;
    private boolean reservation;
    private boolean playground;
    private boolean residential;
    private int management;
    
    private String selected_school;
   
    
    public String viewSchool(String viewschool){
        
        this.selected_school = viewschool;
        
        System.out.print("View School in Filter results: "+viewschool);
        
        return "school?faces-redirect=true";
    }
    
    public String getAs_values_location() {
        return as_values_location;
    }

    public void setAs_values_location(String as_values_location) {
        this.as_values_location = as_values_location;
    }
    
    
     
      
    public String getSelected_school() {
        return selected_school;
    }

    public void setSelected_school(String selected_school) {
        this.selected_school = selected_school;
    }

  
    
     public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public int getMedium() {
        return medium;
    }

    public void setMedium(int medium) {
        this.medium = medium;
    }

    public String getFee_yealry() {
        return fee_yealry;
    }

    public void setFee_yealry(String fee_yealry) {
        this.fee_yealry = fee_yealry;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public int getSchool_type() {
        return school_type;
    }

    public void setSchool_type(int school_type) {
        this.school_type = school_type;
    }

    public boolean isTransportation() {
        return transportation;
    }

    public void setTransportation(boolean transportation) {
        this.transportation = transportation;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public boolean isPlayground() {
        return playground;
    }

    public void setPlayground(boolean playground) {
        this.playground = playground;
    }

    public boolean isResidential() {
        return residential;
    }

    public void setResidential(boolean residential) {
        this.residential = residential;
    }

    public int getManagement() {
        return management;
    }

    public void setManagement(int management) {
        this.management = management;
    }

    
    
    public void filterSearch() throws IOException{
         System.out.print("Coming here filter: "+this.board+","+this.school_type+","+this.standard);
        
    
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
       
         Map<String,String> requestParams = context.getRequestParameterMap();
           
        if(requestParams.get("board").length() > 0)
        this.board = (requestParams.get("board").trim());
        
        if(requestParams.get("fee_yearly").length() > 0)
        this.fee_yealry = (requestParams.get("fee_yearly").trim());
        
        if(requestParams.get("school_type").length() > 0)
        this.school_type  = Integer.parseInt(requestParams.get("school_type"));
        
        if(requestParams.get("standard").length() > 0)
        this.standard = (requestParams.get("standard").trim());
        
        if(requestParams.containsKey("transportation"))
            this.transportation = true;
        
        if(requestParams.containsKey("reservation"))
            this.reservation = true;
        
        if(requestParams.containsKey("residential"))
            this.residential = true;
        
        
        if(requestParams.containsKey("playground"))
            this.playground = true;
        
        
       
        
           
       
       
        
         context.redirect("results.xhtml");
       // return "results";
    }
    
    
    
    
}
