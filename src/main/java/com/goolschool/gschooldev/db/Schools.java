/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.db;

import entity.SchoolMainInfo;
import entity.SchoolSearchInfo;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Named;
import org.javalite.activejdbc.Base;

/**
 *
 * @author Rajeev
 */

@Named
@RequestScoped
public class Schools {
    
    List<SearchResults> school =  new ArrayList<>();
    
    String search_loc = "";

    public Schools() {
        
        FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
        search_loc = (String) faceletContext.getAttribute("search_loc");
        
        System.out.print("initializing schools.. "+search_loc);
        
        String[] parts = search_loc.split(",");
        
        
        String query_con = "";
        
        for(String part: parts){
            if(part.length() > 0){
                
                if(query_con.length() > 0)
                    query_con+= " AND ";
                
                query_con+= " (location LIKE '"+part+"%' OR school_name LIKE '"+part+"%')";
            }
        }
        
        
         if(query_con.length() > 0)
                    query_con= " WHERE "+query_con;
               
        
        
        
        System.out.print("read param");
        
         Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/goolschool", "root", "");
         
            List<SchoolSearchInfo> school_d = SchoolSearchInfo.findBySQL("select * from school_search_info "+query_con+" ORDER BY ranking DESC,user_ranking DESC LIMIT 0,30");
         
            System.out.printf("results :"+school_d.size());
            
            
            
            if(school_d.size() > 0){
                int i=0;
                for(SchoolSearchInfo school_data: school_d){
                 SearchResults search_res = new SearchResults();
                 
                 search_res.setSchool_code(school_data.getString("school_code"));
                 search_res.setSchool_name(school_data.getString("school_name"));
                 search_res.setLocation(school_data.getString("location"));
                 search_res.setCity(school_data.getString("city"));
                 
                 List<SchoolMainInfo> school_main_list = SchoolMainInfo.where("school_code=?", school_data.getString("school_code"));
                
                 if(school_main_list.size() > 0){
                 SchoolMainInfo school_main = school_main_list.get(0);
                 
                 search_res.setEst_year(school_main.getString("establishment_year"));
                 
                 String board = "<a href='#'>"+school_main.getString("affiliation_board_1")+"</a>";
                 
                 if(school_data.getString("affiliation_board_2").length() > 0)
                     board+= ", "+"<a href='#'>"+school_main.getString("affiliation_board_2")+"</a>";
                 
                  if(school_data.getString("affiliation_board_3").length() > 0)
                     board+= ", "+"<a href='#'>"+school_main.getString("affiliation_board_3")+"</a>";
                 
                   if(school_data.getString("affiliation_board_4").length() > 0)
                     board+= ", "+"<a href='#'>"+school_main.getString("affiliation_board_4")+"</a>";
                 
                 
                 
                 
                 
                 
                 search_res.setBoard(board);
                 search_res.setDescription(school_main.getString("school_introduction"));
                 
                 
                     String medium = "<a href='#'>"+school_main.getString("instruction_medium_1")+"</a>";
                 
                 if(school_data.getString("instruction_medium_2").length() > 0)
                     board+= ", "+"<a href='#'>"+school_main.getString("instruction_medium_2")+"</a>";
                 
                  if(school_data.getString("instruction_medium_3").length() > 0)
                     board+= ", "+"<a href='#'>"+school_main.getString("instruction_medium_3")+"</a>";
                 
                   if(school_data.getString("instruction_medium_4").length() > 0)
                     board+= ", "+"<a href='#'>"+school_main.getString("instruction_medium_4")+"</a>";
                 
                 
                 
                 search_res.setMedium(medium);
                 search_res.setSchool_contact(school_main.getString("phone_number_1"));
                 search_res.setSchool_email(school_main.getString("email_id_1"));
                 search_res.setSchool_website(school_main.getString("website_url"));
                 }
                 
                 search_res.setRating(school_data.getString("user_rating"));
                 
                 school.add(search_res);
                  
                    //    ret+= "-School Name:"+school_data.getString("school_name")+"-School Code:"+school_data.get("school_code");
                }   
                
                
                      //   ret.put(fieldName, tempar);
                    
            }
            
         Base.close();
        
        
    }

    public String getSearch_loc() {
        return search_loc;
    }

    public void setSearch_loc(String search_loc) {
        this.search_loc = search_loc;
    }
    
    

    public List<SearchResults> getSchool() {
        return school;
    }

    public void setSchool(List<SearchResults> school) {
        this.school = school;
    }
    
    
    
    
    
}
