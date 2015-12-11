/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.util;

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
         JSONArray loclist = new JSONArray() ;

         slist = listSchool("school_name",to);
        loclist = listSchool("location",to);
            
        
             
           for(int i=0;i < (slist.size());i++){
                  loclist.add(slist.get(i));
           }
           
        
        
        return  loclist.toJSONString();
    }
    
    
    
      
    /* Method to  READ all the employees using Scalar Query */
   public JSONArray listSchool (String fieldName,String fieldVal ){
    
     // Transaction tx = null;
     // JSONObject ret = new JSONObject() ;
      JSONObject temp = new JSONObject() ;
      
      JSONArray tempar = new JSONArray();
       try{
           
                   
        // Class.forName("com.mysql.jdbc.Driver");  
         
         Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/goolschool", "root", "");
         
            List<SchoolSearchInfo> school = SchoolSearchInfo.findBySQL("select * from school_search_info WHERE "+fieldName+" LIKE '"+fieldVal+"%' LIMIT 0,5");
         
            System.out.printf("results :"+school.size());
            
            if(school.size() > 0){
                int i=0;
                for(SchoolSearchInfo school_data: school){
                 temp.put("name",toTitleCase(school_data.get(fieldName).toString()));
                         //+","+school_data.get("location"));
                 temp.put("value",school_data.get(fieldName));
                 // temp.put("location",school_data.get("location"));
                 
                 tempar.add(temp);
                    
                //    ret+= "-School Name:"+school_data.get("school_name")+"-School Code:"+school_data.get("school_code");
                }   
                
                
                      //   ret.put(fieldName, tempar);
                    
            }
            
         Base.close();

         
         
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
