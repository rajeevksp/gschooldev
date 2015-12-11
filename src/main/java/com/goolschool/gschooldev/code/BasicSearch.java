/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.code;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
  
  
//import javax.inject.Named;
/**
 *
 * @author Rajeev
 */
@ManagedBean(name = "basicSearch")
@SessionScoped
public class BasicSearch implements Serializable{
    
    private String as_values_location;

    public String getAs_values_location() {
        return as_values_location;
    }

    public void setAs_values_location(String as_values_location) {
        this.as_values_location = as_values_location;
    }
    
    public String searchResults(){
         System.out.print("Search for "+this.as_values_location);
        return "results?faces-redirect=true";
    }
    
    public void submitSearch() throws IOException{
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
       
         Map<String,String> requestParams = context.getRequestParameterMap();
        
        
        System.out.print("Coming here "+ requestParams.get("as_values_location"));
        
        this.as_values_location = (requestParams.get("as_values_location").trim());
        
       
        
         context.redirect("results.xhtml");
       // return "results";
    }
    
    public void saveUserSearch(){
        
    }
    
    
}
