/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.util;

import com.goolschool.gschooldev.db.DbCon;
import entity.SchoolSearchInfo;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import org.javalite.activejdbc.Base;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * REST Web Service
 *
 * @author Rajeev
 */
@Path("autoComplete")
public class apiClass {

    @Context
    private UriInfo context;
    private DbCon db;
    /**
     * Creates a new instance of apiClass
     */
    public apiClass() {
    }

    /**
     * Retrieves representation of an instance of com.goolschool.gschooldev.db.apiClass
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String callapi(@Context UriInfo info){
         String to = info.getQueryParameters().getFirst("to");
           Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        
   
         JSONArray slist = new JSONArray() ;
        // JSONArray loclist = new JSONArray() ;

         slist = listSchool(to);
     //   loclist = listSchool("location",to);
            
        System.out.print("results: "+slist.toJSONString());
             
       //    for(int i=0;i < (slist.size());i++){
       //           loclist.add(slist.get(i));
        //   }
           
        
        
        return  slist.toJSONString();
    }
    
    
    
      
    /* Method to  READ all the employees using Scalar Query */
   public JSONArray listSchool (String fieldVal ){
    
     // Transaction tx = null;
     // JSONObject ret = new JSONObject() ;
    //  JSONObject temp = new JSONObject() ;
      
      JSONArray tempar = new JSONArray();
       try{
           
                   
        // Class.forName("com.mysql.jdbc.Driver");  
         db = new DbCon();
           
            List<SchoolSearchInfo> school = SchoolSearchInfo.findBySQL("select DISTINCT(location) from school_search_info WHERE location LIKE '"+fieldVal+"%' OR school_name LIKE '"+fieldVal+"%' LIMIT 0,5");
           int i=0;
            if(school.size() > 0){
                
                for(SchoolSearchInfo school_data: school){
                     JSONObject temp = new JSONObject() ;
      
                  temp.put("name",toTitleCase(school_data.get("location").toString()));
                  temp.put("value",school_data.get("location"));
                  
                   tempar.add(i, temp);
                   i++;
                 }   
                       
            }
            
          //  System.out.print("location: "+tempar.toJSONString());
            
           List<SchoolSearchInfo> school_spon = SchoolSearchInfo.findBySQL("select DISTINCT(school_name) from school_search_info WHERE location LIKE '"+fieldVal+"%' OR school_name LIKE '"+fieldVal+"%' LIMIT 0,5");
           
            if(school_spon.size() > 0){
                
                for(SchoolSearchInfo school_data: school_spon){
                     JSONObject temp = new JSONObject() ;
      
                 temp.put("name",toTitleCase(school_data.get("school_name").toString()));
                  temp.put("value",school_data.get("school_name"));
                  
                   tempar.add(i, temp);
                   i++;
                    
                 }   
                       
            }
            
            System.out.print("school: "+tempar.toJSONString());
            
        db.closeDb();
         
         
       }
       catch(NullPointerException ex){
           ex.printStackTrace();
       }
       
      return tempar;
   }
   
   
   
   public static String toTitleCase(String givenString) {
    String[] arr = givenString.split(" ");
    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < arr.length; i++) {
        sb.append(Character.toUpperCase(arr[i].charAt(0)))
            .append(arr[i].substring(1)).append(" ");
    }          
    return sb.toString().trim();
}  
}
