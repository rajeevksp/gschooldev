/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.db;

import com.goolschool.gschooldev.util.SchoolFacilities;
import com.goolschool.gschooldev.util.StringList;
import entity.InstructionMedium;
import entity.SchoolFacilitiesInfo;
import entity.SchoolMainInfo;
import entity.SchoolSearchInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
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
    List<StringList> strlist = new ArrayList<>();
    
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

    
    DbCon db;

    
    
    
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
    
    
    public List<StringList> getStrlist() {
        return strlist;
    }

    public void setStrlist(List<StringList> strlist) {
        this.strlist = strlist;
    }
    
    String search_loc = "";

    public Schools() {
        
        FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
        
        
        
        search_loc = (String) faceletContext.getAttribute("search_loc");
        
        System.out.print("initializing schools.. "+search_loc);
        
        String[] parts = search_loc.split(",");
        
         List<SearchResults> scd =  new ArrayList<>();
    
         String scodes = "";
         //Change this condition for advertiser_rank
        String query_con = "";
        
        for(String part: parts){
            if(part.length() > 0){
                
                if(query_con.length() > 0)
                    query_con+= " OR ";
                
                query_con+= " (school_search_info.location LIKE '"+part+"%' OR school_search_info.school_name LIKE '"+part+"%')";
            }
        }
        
        
        
     
               
           db = new DbCon();
           
          //List sponsored schools first
          String final_con = " school_search_info.school_code = school_main_info.school_code AND school_main_info.advertiser_rank < 10";
        
         if(query_con.length() > 0)
                    final_con= " WHERE "+final_con+" AND ("+query_con+")";
         
            List<SchoolSearchInfo> school_d = SchoolSearchInfo.findBySQL("select school_search_info.*,school_search_info.school_code from school_search_info,school_main_info "+final_con+" ORDER BY advertiser_rank ASC,ranking DESC,user_ranking DESC LIMIT 0,2");
          
            
            if(school_d.size() > 0){
               
                for(SchoolSearchInfo school_data: school_d){
                    scodes+= "'"+school_data.getString("school_code")+"',";
                  scd.add(this.schoolInfo(school_data,1));
             
                }   
                     
            }
            
            //List other schools
            
          final_con = "";
          if(scodes.length() > 0)
          final_con = " school_search_info.school_code NOT IN("+scodes.substring(0,scodes.length()-1)+")  AND ";
        
         if(query_con.length() > 0)
                    final_con= " WHERE "+final_con+" ("+query_con+")";
            List<SchoolSearchInfo> school_d2 = SchoolSearchInfo.findBySQL("select * from school_search_info "+final_con+" ORDER BY  ranking DESC,user_ranking DESC LIMIT 0,30");
          
            
            if(school_d2.size() > 0){
               
                for(SchoolSearchInfo school_data: school_d2){
                  scd.add(this.schoolInfo(school_data,0));
             
                }   
                     
            }
            
            
             this.school = scd;
        
             db.closeDb();
        
    }

    
    
    
    public SearchResults schoolInfo(SchoolSearchInfo school_data,int sponsored){
        
        
         SearchResults search_res = new SearchResults();
                 
                 search_res.setSchool_code(school_data.getString("school_code"));
                 search_res.setSchool_name(school_data.getString("school_name"));
                 search_res.setLocation(school_data.getString("location"));
                 search_res.setCity(school_data.getString("city"));   
                 search_res.setSchool_type(school_data.getInteger("school_type"));
                 search_res.setSponsored_result(sponsored);
                
                  search_res.setRating(school_data.getString("user_ranking"));
                 
                  String board =  school_data.getString("affiliation_board_1") ;
                 
                 if(school_data.getString("affiliation_board_2").length() > 0)
                     board+= ", "+ school_data.getString("affiliation_board_2") ;
                 
                  if(school_data.getString("affiliation_board_3").length() > 0)
                     board+= ", " +school_data.getString("affiliation_board_3") ;
                 
                   if(school_data.getString("affiliation_board_4").length() > 0)
                     board+= ", " +school_data.getString("affiliation_board_4") ;
                 
                 System.out.print("board: "+board);
                 search_res.setBoard(board);
                 
                 
                 
                    List<SchoolMainInfo> school_main_list = SchoolMainInfo.where("school_code=?", school_data.getString("school_code"));
              
                    
                 
                 if(school_main_list.size() > 0){
                 SchoolMainInfo school_main = school_main_list.get(0);
                 
                 search_res.setEst_year(school_main.getString("establishment_year"));
                 
                 
                 String description = school_main.getString("school_introduction");
                 
                 if(description.length() > 50){
                     description = description.substring(0,50)+"...";
                 }
                 else if(description.length() == 0){
                     description = school_data.getString("school_name")+" was established in "+school_main.getString("establishment_year");
                 }
                
                 search_res.setDescription(description);
                 
                 
                 
                 
                     String medium = school_main.getString("instruction_medium_1");
                 
                 if(school_main.getString("instruction_medium_2").length() > 0)
                     board+= ", "+school_main.getString("instruction_medium_2");
                 
                  if(school_main.getString("instruction_medium_3").length() > 0)
                     board+= ", "+school_main.getString("instruction_medium_3");
                 
                   if(school_main.getString("instruction_medium_4").length() > 0)
                     board+= ", "+school_main.getString("instruction_medium_4");
                 
                 
                 
                 search_res.setMedium(medium);
                 search_res.setSchool_contact(school_main.getString("phone_number_1"));
                 search_res.setSchool_email(school_main.getString("email_id_1"));
                 search_res.setSchool_website(school_main.getString("website_url"));
                  search_res.setSchool_logo(school_main.getString("thumbnail_url"));
                  search_res.setDescription(school_main.getString("school_introduction"));
                 
                 }
                 
                 return search_res;
        
    }
    
    
    public  SchoolFacilities  schoolFacilities(String school_code,String property){
        
          SchoolFacilities  facilities = new SchoolFacilities();
         db = new DbCon();
       
           List<SchoolFacilitiesInfo> school_facility_list = SchoolFacilitiesInfo.where("school_code=?", school_code);
           
           if(school_facility_list.size() > 0){
               
               SchoolFacilitiesInfo facility = school_facility_list.get(0);
                 SchoolFacilities fc = new SchoolFacilities();
                 
                 
                 
              switch(property){
                  
                   
                  
                   case "computers":
                 
                   fc.setFacility("Computers");
                 if(facility.getInteger("no_of_computers") > 0)
                     fc.setStatus(1);
                 else
                     fc.setStatus(0);
                 
                 fc.setVal(String.valueOf(facility.getInteger("no_of_computers")));
                  
                  break;
                  
                   case "library":
                 
                   fc.setFacility("Library");
                 if(facility.getBoolean("library_present"))
                     fc.setStatus(1);
                 else
                     fc.setStatus(0);
                 
                 fc.setVal(String.valueOf(facility.getInteger("no_of_books_lib")));
                  
                  break;
                  
                   case "playground":
                 
                   fc.setFacility("Play ground");
                 if(facility.getBoolean("playground_present"))
                     fc.setStatus(1);
                 else
                     fc.setStatus(0);
                 
                 fc.setVal(String.valueOf(facility.getDouble("playground_latitude"))+","+String.valueOf(facility.getDouble("playground_longitude")));
                  
                  break;
                  
                   case "medical_checkup":
                 
                   fc.setFacility("Medical Check-up");
                 if(facility.getBoolean("medical_checkup")){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                  
                  break;
                  
                   case "food_served":
                 
                        fc.setFacility("Food Served");
                 if(facility.getBoolean("food_served")){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                    
                    break;
                  
                   case "cafeteria":
                 
                          fc.setFacility("Cafeteria");
                 if(facility.getBoolean("cafeteria_present")){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                    
                    break;
                  
                   case "cctv":
                 
                          fc.setFacility("CCTV");
                 if(facility.getBoolean("cctv_present")){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                   
                    break;
                  
                   case "cctv_class":
                 
                          fc.setFacility("CCTV Class");
                 if(facility.getBoolean("cctv_class_present")){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                    
                    break;
                  
                   case "internet":
                 
                 fc.setFacility("Internet");
                 if(facility.getBoolean("internet")){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                    
                   
                    break;
                  
                   case "security":
                 
                   fc.setFacility("Security");
                 if(facility.getBoolean("security")){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                    
                   
                    break;
                  
                   case "auditorium":
                 
                   
                   fc.setFacility("Auditorium");
                 if(facility.getBoolean("internet")){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                   
                   
                    break;
                  
                   case "swimming_pool":
                 
                   
                   fc.setFacility("Swimming Pool");
                 if(facility.getBoolean("swimming_pool")){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                   
                   break;
                       
                  
                   case "hostel":
                 
                   
                   fc.setFacility("Hostel");
                 if(facility.getBoolean("boys_hostel")   || facility.getBoolean("girls_hostel")  ){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                   
                   break;
                       
                       
                       
                  case "physics_lab":
                 
                   
                   fc.setFacility("Physics Lab");
                 if(facility.getBoolean("physics_lab")  ){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                   
                   break;
                 
                   case "chemistry_lab":
                 
                   
                   fc.setFacility("Chemistry Lab");
                 if(facility.getBoolean("chemistry_lab")  ){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                   
                   break;
                           
                  case "biology_lab":
                 
                   
                   fc.setFacility("Biology Lab");
                 if(facility.getBoolean("biology_lab")  ){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                   
                   break;
                      
                      
                case "extra_curricular_activities":
                 
                   
                   fc.setFacility("Extra Curricular Activities");
                 if(facility.getString("extra_curricular_activities").length() > 0){
                     fc.setStatus(1);
                     fc.setVal(facility.getString("extra_curricular_activities"));
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                   
                   break;
                    
                    
               
                case "transportation":
                 
                   
                   fc.setFacility("Transportation");
                 if(facility.getBoolean("transport_provided")  ){
                     fc.setStatus(1);
                     fc.setVal("Available");
                 }
                 else{
                     fc.setStatus(0);
                     fc.setVal("Not Available");
                 }
                   
                   break;
               }
                
              
              facilities = fc;
           
            }
               
         
         
         
        db.closeDb();
        return facilities;
    }
    
    
    public List<StringList> stringToList(String str){
        List<StringList> list_str = new ArrayList<>();
        
        
         String [] bstr = str.split(",");
         
         
         for(int i=0;i < bstr.length;i++){
          
             StringList strl = new StringList();
             strl.setStr(bstr[i]);
             if(i < bstr.length-1)
                 strl.setTrail(", ");
             else
                 strl.setTrail("");
             
             list_str.add(strl);
             
             
         }
         
                 
        return list_str;
    }
    
    
    
    public String getMedium(String medium_id){
        
        String medium = "";
        
          db = new DbCon();
       
          
        List<InstructionMedium> med = InstructionMedium.where("code=?", medium_id);
         
        
        if(med.size() > 0){
            InstructionMedium med_data = med.get(0);
            
            medium = med_data.getString("language");
        }
        
        
        db.closeDb();
        
        return medium;
        
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
