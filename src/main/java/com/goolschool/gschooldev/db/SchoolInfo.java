/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.db;

import com.goolschool.gschooldev.util.SchoolFacilities;
import com.goolschool.gschooldev.util.StringList;
import com.goolschool.gschooldev.util.apiClass;
import entity.SchoolFacilitiesInfo;
import entity.SchoolMainInfo;
import entity.SchoolSearchInfo;
import java.util.ArrayList;
 
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Named;

/**
 *
 * @author Rajeev
 */
@Named
@RequestScoped
public class SchoolInfo  {
    
    
    private String school_code;
    private String school_name ;
    private String school_type;
    private int entity_type = 0;
    private int school_category;
    private String rating;
    private String user_rating;
    private String est_year;
    private String medium;
    private String board;
    
    private String full_address;
    private String district_pin;
    private String location ;
    private String city ;
    private double latitude;
    private double longitude;
    
    private String school_contact;
    private String school_email;
    private String school_website;
   
    private String description;
    private String school_logo;
    private String gallery_url;
    private String video_url;
    
    private String pre_school_timings;
    private String primary_school_timings;
    private String high_school_timings;
    private String school_management;
    private String ptr;
    private String recognized_by;
    
    private List<SchoolFacilitiesInfo> school_facility_list;
    
    private List<String> quickfacts;

    private String academic_activities;
    private String curricular_activities;

    DbCon db;

    public String getDistrict_pin() {
        return district_pin;
    }

    public void setDistrict_pin(String district_pin) {
        this.district_pin = district_pin;
    }

    public SchoolInfo() {
        
       FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
         
         
        this.school_code = (String) faceletContext.getAttribute("school_code");
        
        db = new DbCon();
        
        
        List<SchoolSearchInfo> school_search = SchoolSearchInfo.where("school_code=?", school_code);
        
        if(school_search.size() > 0){
            SchoolSearchInfo school_search_data = school_search.get(0);
          
            
            this.school_name = apiClass.toTitleCase(school_search_data.getString("school_name"));
              String b =  school_search_data.getString("affiliation_board_1") ;
                 
                 if(school_search_data.getString("affiliation_board_2").length() > 0)
                     b+= ", "+ school_search_data.getString("affiliation_board_2") ;
                 
                  if(school_search_data.getString("affiliation_board_3").length() > 0)
                     b+= ", " +school_search_data.getString("affiliation_board_3") ;
                 
                   if(school_search_data.getString("affiliation_board_4").length() > 0)
                     b+= ", " +school_search_data.getString("affiliation_board_4") ;
            
             this.board = b;
             
           this.rating = school_search_data.getString("ranking");
           this.user_rating = school_search_data.getString("user_ranking");
            this.school_type = school_search_data.getString("school_type");
            
            
       }
        
        List<SchoolMainInfo> school_main = SchoolMainInfo.where("school_code=?", school_code);
        
        if(school_main.size() > 0){
            SchoolMainInfo school_data = school_main.get(0);
            
            if(school_data.getBoolean("entity_type")){
            this.entity_type = 1;
            }
            
            
            this.full_address = apiClass.toTitleCase(school_data.getString("address_line_1") +", "+ school_data.getString("address_line_1"))+", ";
            this.city = apiClass.toTitleCase(school_data.getString("city"))+",";
            this.location = apiClass.toTitleCase(school_data.getString("location"));
            this.district_pin = apiClass.toTitleCase( school_data.getString("district"))+", "+ apiClass.toTitleCase(school_data.getString("state"))+"-"+school_data.getString("pincode");
            this.school_contact = school_data.getString("phone_numbe_1")+","+school_data.getString("phone_numbe_2");
            this.school_email = school_data.getString("email_id_1");
            this.school_website = school_data.getString("website_url");
            this.est_year = school_data.getString("establishment_year");
            this.school_logo = school_data.getString("thumbnail_url");
            
              
                 String description_txt = school_data.getString("school_introduction");
                 
                 if(description_txt.length() > 50){
                     this.description = description_txt.substring(0,50)+"...";
                 }
                 else if(description_txt.length() == 0){
                     this.description =  school_name+" was established in "+est_year;
                 }
            
                    String medium_txt = school_data.getString("instruction_medium_1");
                 
                 if(school_data.getString("instruction_medium_2").length() > 0)
                     medium_txt+= ", "+school_data.getString("instruction_medium_2");
                 
                  if(school_data.getString("instruction_medium_3").length() > 0)
                     medium_txt+= ", "+school_data.getString("instruction_medium_3");
                 
                   if(school_data.getString("instruction_medium_4").length() > 0)
                     medium_txt+= ", "+school_data.getString("instruction_medium_4");
                 
                   this.medium = medium_txt;

               this.pre_school_timings = school_data.getString("timings_preschool");
               this.primary_school_timings = school_data.getString("timings_primary");
               this.high_school_timings = school_data.getString("timings_higher");
               
               this.ptr = school_data.getString("teacher_student_ratio");
               
               if(school_data.getBoolean("school_recognized"))
               this.recognized_by = school_data.getString("recognized_by");
               
               
                
        
       }
        
        
        db.closeDb();
    }
   
    
    
    
        
    public  SchoolFacilities  schoolFacilities(String school_code,String property){
        
          SchoolFacilities  facilities = new SchoolFacilities();
         db = new DbCon();
       
           List<SchoolFacilitiesInfo> school_facilities = SchoolFacilitiesInfo.where("school_code=?", school_code);
           
           if(school_facilities.size() > 0){
               
               SchoolFacilitiesInfo facility = school_facilities.get(0);
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
    

    
    public List<String> createFacts(){
        
        quickfacts = new ArrayList<>();
        
        
        return quickfacts;
        
    }
    
    public String getSchool_code() {
        return school_code;
    }

    public void setSchool_code(String school_code) {
        this.school_code = school_code;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getSchool_type() {
        return school_type;
    }

    public void setSchool_type(String school_type) {
        this.school_type = school_type;
    }

    public int getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(int entity_type) {
        this.entity_type = entity_type;
    }

    public int getSchool_category() {
        return school_category;
    }

    public void setSchool_category(int school_category) {
        this.school_category = school_category;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(String user_rating) {
        this.user_rating = user_rating;
    }

    public String getEst_year() {
        return est_year;
    }

    public void setEst_year(String est_year) {
        this.est_year = est_year;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSchool_contact() {
        return school_contact;
    }

    public void setSchool_contact(String school_contact) {
        this.school_contact = school_contact;
    }

    public String getSchool_email() {
        return school_email;
    }

    public void setSchool_email(String school_email) {
        this.school_email = school_email;
    }

    public String getSchool_website() {
        return school_website;
    }

    public void setSchool_website(String school_website) {
        this.school_website = school_website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchool_logo() {
        return school_logo;
    }

    public void setSchool_logo(String school_logo) {
        this.school_logo = school_logo;
    }

    public String getGallery_url() {
        return gallery_url;
    }

    public void setGallery_url(String gallery_url) {
        this.gallery_url = gallery_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getPre_school_timings() {
        return pre_school_timings;
    }

    public void setPre_school_timings(String pre_school_timings) {
        this.pre_school_timings = pre_school_timings;
    }

    public String getPrimary_school_timings() {
        return primary_school_timings;
    }

    public void setPrimary_school_timings(String primary_school_timings) {
        this.primary_school_timings = primary_school_timings;
    }

    public String getHigh_school_timings() {
        return high_school_timings;
    }

    public void setHigh_school_timings(String high_school_timings) {
        this.high_school_timings = high_school_timings;
    }

    public String getSchool_management() {
        return school_management;
    }

    public void setSchool_management(String school_management) {
        this.school_management = school_management;
    }

    public String getPtr() {
        return ptr;
    }

    public void setPtr(String ptr) {
        this.ptr = ptr;
    }

    public String getRecognized_by() {
        return recognized_by;
    }

    public void setRecognized_by(String recognized_by) {
        this.recognized_by = recognized_by;
    }

    public String getAcademic_activities() {
        return academic_activities;
    }

    public void setAcademic_activities(String academic_activities) {
        this.academic_activities = academic_activities;
    }

    public String getCurricular_activities() {
        return curricular_activities;
    }

    public void setCurricular_activities(String curricular_activities) {
        this.curricular_activities = curricular_activities;
    }
    
    
    
    
  
    
}
