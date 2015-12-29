/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.code;

import com.goolschool.gschooldev.db.DbCon;
 
import com.goolschool.gschooldev.util.apiClass; 
import entity.UserSearch;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map; 
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.javalite.activejdbc.Model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
  
  
//import javax.inject.Named;
/**
 *
 * @author Rajeev
 */
@ManagedBean(name = "basicSearch")
@SessionScoped
public class BasicSearch implements Serializable{
    
    private String as_values_location;

    private String entity;
    
    private String user_location;
    private String target_class = "";
    private String board = "";
    private String fee_range = "";
    private String school_type = "";
    private String user_fullname;
    private String user_email;
    private String user_mobile;
    private String user_salary;
    
 
    private String elder_child_name;
    private int transportation = 1;
    private int reservation = 0;
    private int job_transferable = 0;
    private int accept_terms = 0;
    private String standard;
    private int medium = 1;
    private int playground = 0;
    private int residential = 0;
    private int management = 1;

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

    public int isPlayground() {
        return playground;
    }

    public void setPlayground(int playground) {
        this.playground = playground;
    }

    public int isResidential() {
        return residential;
    }

    public void setResidential(int residential) {
        this.residential = residential;
    }

    public int getManagement() {
        return management;
    }

    public void setManagement(int management) {
        this.management = management;
    }

    public String getUser_location() {
        return user_location;
    }

    public void setUser_location(String user_location) {
        this.user_location = user_location;
    }

    public String getTarget_class() {
        return target_class;
    }

    public void setTarget_class(String target_class) {
        this.target_class = target_class;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getFee_range() {
        return fee_range;
    }

    public void setFee_range(String fee_range) {
        this.fee_range = fee_range;
    }

    public String getSchool_type() {
        return school_type;
    }

    public void setSchool_type(String school_type) {
        this.school_type = school_type;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getUser_salary() {
        return user_salary;
    }

    public void setUser_salary(String user_salary) {
        this.user_salary = user_salary;
    }

 
    public String getElder_child_name() {
        return elder_child_name;
    }

    public void setElder_child_name(String elder_child_name) {
        this.elder_child_name = elder_child_name;
    }

    public int getTransportation() {
        return transportation;
    }

    public void setTransportation(int transportation) {
        this.transportation = transportation;
    }

    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    public int getJob_transferable() {
        return job_transferable;
    }

    public void setJob_transferable(int job_transferable) {
        this.job_transferable = job_transferable;
    }

    public int getAccept_terms() {
        return accept_terms;
    }

    public void setAccept_terms(int accept_terms) {
        this.accept_terms = accept_terms;
    }
    
    
    
    

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
    
    
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
        
        
        System.out.print("Coming here "+ requestParams.get("as_values_location")+" others: "+requestParams.get("entity_type"));
        
        this.as_values_location = (requestParams.get("as_values_location").trim());
        
       
        this.entity= requestParams.get("entity_type").trim();
        
        
        context.redirect("results.xhtml");
       // return "results";
    }
    
    
    
    
    public void submitUserSearch()throws IOException{
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
       
        Map<String,String> requestParams = context.getRequestParameterMap();
        
         System.out.print("Printing form values: -- "+requestParams.toString());
        
        
             this.user_mobile = requestParams.get("user_mobile").trim();
         
       this.as_values_location = this.user_location = (requestParams.get("as_values_userLocation").trim());
       this.school_type =  (requestParams.get("school_type").trim());
       this.entity= requestParams.get("school_entity").trim();
        
       this.user_fullname = requestParams.get("full_name").trim();
       this.user_email = requestParams.get("user_email").trim();
       this.user_salary = requestParams.get("salary_range").trim();
       this.user_mobile = requestParams.get("user_mobile").trim();
       this.fee_range = requestParams.get("fee_range").trim();
       this.board = requestParams.get("board").trim();
      
       if(requestParams.containsKey("transportation"))
           this.transportation = 1;
       
       if(requestParams.containsKey("job_transferable"))
           this.job_transferable = 1;
       
         if(requestParams.containsKey("accept_terms"))
           this.accept_terms = 1;
        
        if(requestParams.containsKey("reservation"))
           this.reservation = 1;
        
        if(requestParams.containsKey("anotherChild"))
           this.elder_child_name =requestParams.get("sibling_school").trim();
        
       saveUserSearch();
     
       
        
        context.redirect("results.xhtml");
    }
    
    
    
    
    
    public void saveUserSearch(){
        
       DbCon db = new DbCon();
       
       int entity_type = 0;
      
       
       if(entity.equals("nursery"))
           entity_type = 1;
       
       
       
          //is client behind something?
       
   HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
String ipAddress = request.getHeader("X-FORWARDED-FOR");
if (ipAddress == null) {
    ipAddress = request.getRemoteAddr();
}



java.util.Date dt = new java.util.Date();

java.text.SimpleDateFormat sdf = 
     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

String currentTime = sdf.format(dt);

         UserSearch searchData = new UserSearch();
         searchData.set("search_locality",user_location);
         searchData.set("entity_type",entity_type);
         searchData.set("search_board",board);
         searchData.set("search_school_type",Integer.parseInt(school_type));
         searchData.set("user_fullname",user_fullname);
         searchData.set("user_email",user_email);
         searchData.set("user_mobile",user_mobile);
         searchData.set("user_salary",user_salary);
         searchData.set("user_sibling",elder_child_name);
         searchData.set("user_job_transferable",job_transferable);
         searchData.set("search_transport",transportation);
         searchData.set("search_reservation",reservation);
         searchData.set("user_ip",ipAddress);
         searchData.set("timestamp",currentTime);
       
        searchData.save();
       
       db.closeDb();
        
    }
    
    
    public JSONArray strToJson(String str){
        
        JSONArray jsonar = new JSONArray();
        JSONObject json = new JSONObject();
         
           String[] str_array = str.split(",");
           
           for(String val:str_array){
               json.put("name", apiClass.toTitleCase(val));
                json.put("value", val);
                jsonar.add(json);
           }
        
        
        return jsonar;
         
        
    }
    
}


