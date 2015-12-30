/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.util;

import com.goolschool.gschooldev.db.DbCon;
import com.goolschool.gschooldev.db.SchoolInfo;
import entity.SchoolMainInfo;
import entity.SchoolSearchInfo;
import java.util.List;
 
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
 
import javax.ws.rs.Produces;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path; 

/**
 * REST Web Service
 *
 * @author Rajeev
 */
@Path("ajaxprocess")
public class ajaxProcess {

    @Context
    private UriInfo context;
    private DbCon db;
    /**
     * Creates a new instance of apiClass
     */
    public ajaxProcess() {
    }

 
    @GET
    @Produces("text/html")
    public String callapi(@Context UriInfo info){
         String school_code = info.getQueryParameters().getFirst("school_code");
          

       String ret_html = "";
        
              db = new DbCon();
        
        
        List<SchoolSearchInfo> school_search = SchoolSearchInfo.where("school_code=?", school_code);
                    
              
 List<SchoolMainInfo> school_main_list = SchoolMainInfo.where("school_code=?", school_code);
                
        if(school_search.size() > 0){
            
            SchoolSearchInfo school_data = school_search.get(0);
             SchoolMainInfo school_main = school_main_list.get(0);
             
             int total_students = school_main.getInteger("student_count_preprimary")+school_main.getInteger("student_count_boys")+school_main.getInteger("student_count_girls");
             int ptr = school_main.getInteger("teacher_student_ratio");
             boolean cce = school_main.getBoolean("cce");
             boolean pcr = school_main.getBoolean("pcr");
             boolean smc = school_main.getBoolean("smc_present");
             int smc_m = school_main.getInteger("smc_parent_m");
             int smc_f = school_main.getInteger("smc_parent_f");
             int num_inst_chain = school_data.getInteger("num_inst_chain");
                 String b =  "<a href=\""+school_data.getString("affiliation_board_1")+"\">"+school_data.getString("affiliation_board_1")+"</a>" ;
                 
                 if(school_data.getString("affiliation_board_2").length() > 0)
                     b+= ", "+ "<a href=\""+school_data.getString("affiliation_board_2")+"\">"+school_data.getString("affiliation_board_2") +"</a>" ;
                 
                  if(school_data.getString("affiliation_board_3").length() > 0)
                     b+= ", " +"<a href=\""+school_data.getString("affiliation_board_3")+"\">"+school_data.getString("affiliation_board_3") +"</a>" ;
                 
                   if(school_data.getString("affiliation_board_4").length() > 0)
                     b+= ", " +"<a href=\""+school_data.getString("affiliation_board_4")+"\">"+school_data.getString("affiliation_board_4")+"</a>" ;
                   
               String medium = "<a href=\""+school_data.getString("instruction_medium_1")+"\">"+school_main.getString("instruction_medium_1")+"</a>";
                 
                 if(school_main.getString("instruction_medium_2").length() > 0)
                     medium+= ", "+"<a href=\""+school_data.getString("instruction_medium_1")+"\">"+school_main.getString("instruction_medium_2")+"</a>";
                 
                  if(school_main.getString("instruction_medium_3").length() > 0)
                     medium+= ", "+"<a href=\""+school_data.getString("instruction_medium_1")+"\">"+school_main.getString("instruction_medium_3")+"</a>";
                 
                   if(school_main.getString("instruction_medium_4").length() > 0)
                     medium+= ", "+"<a href=\""+school_data.getString("instruction_medium_1")+"\">"+school_main.getString("instruction_medium_4")+"</a>";
                 
                       
                   
                   
             String ptr_rating = "less";
                if(ptr <= 25)
                    ptr_rating = "Good";

                else if(ptr > 25 && ptr <35)
                    ptr_rating = "Moderate";

             
             
             ret_html+="<a class=\"closeView\" onClick=\"$('#schoolView').hide();\"><i class=\"fa fa-times fa-2x\"></i></a><div class=\"panel panel-primary\">" +
"                    " +
"                        <div class=\"panel-body\">" +
"                               " +
"                               <div class=\"col-sm-4 vschoolProfilePic\">" +
"                                          <img src=\""+school_main.getString("thumbnail_url")+"\"  alt=\""+school_data.getString("school_name")+"\" />" +
"                                 </div>" +
"                                 <div class=\"col-sm-8\">" +
"                                     <div class=\"row schoolProfileRating\">" +
"                                                      <div class=\"col-sm-2 check\">           " +
"                                                             <a class=\"btn btn-warning   btn-fab btn-raised mdi-action-grade\" data-toggle=\"tooltip\" title=\"Overall Rating\" href=\"javascript:void(0)\"></a>" +
"                                                      </div>" +
"                                                      <div class=\"col-sm-4 rating\"><h2>"+school_data.getString("ranking")+"</h2></div>" +
"                                                      <div class=\"col-sm-2 check\">           " +
"                                                             <a class=\"btn btn-success   btn-fab btn-raised mdi-action-grade\" data-toggle=\"tooltip\" title=\"User Rating\" href=\"javascript:void(0)\"></a>" +
"                                                      </div>" +
"                                                      <div class=\"col-sm-4 rating\"><h2>"+school_data.getString("user_ranking")+"</h2></div>   " +
                                                      
"                            </div>" +
"                            " +
"                              <div >Established: <b>"+school_main.getString("establishment_year")+"</b></div>" +
"                                              <div >Board: <b>"+b+"</b></div>" +
"                                           <div >Medium: <b>"+medium+"</b></div>" +
"                                          " +
"                                 </div>" +
"                                      " +
"                        </div>" +
"                            <div class=\"panel-footer\">" +
"                            <h2 class=\"panel-title\">"+school_data.getString("school_name")+"</h2>" +
"                            <h5><i class=\"fa fa-map-marker\"></i> <span class=\"loc\">" +school_data.getString("location")+"</span>, " +school_data.getString("city")+"</h5>" +
"                        </div>" +
"                    </div>"+
                      "<div class=\"schoolProfileSummary\">"
                     + "<h4>Quick Facts</h4>"+
                      "<ul class=\"quickFacts\" >"+
                      "<li> Total Enrolled Students: "+total_students+"</li>" +
                      "<li> Individual attention on students is  "+ptr_rating+" as PTR stands at "+ptr+":1</li>" +
                     "<li> This institution concentrates on Holistic development of students</li>" ;
             
                     if(cce)
                     ret_html+= "<li> School follows Continuous and Comprehensive Evaluation (CCE) for student assessment</li>";
                     if(pcr)
                     ret_html+= "<li> School follows regular academic ranking for student assessment</li>";
                     
                     if(smc)
                     ret_html+= "<li> This school maintains a good number of "+(smc_m+smc_f)+" parents in School Management Committee meetings to take decisions.</li>";
                     
                     if(num_inst_chain <= 1)
                         ret_html+= "<li> This school operates independently and offers "+b+" Curriculum</li>";
                     else
                         ret_html+= "<li> This school operates  with multiple branches and offers "+b+" Curriculum</li>";
                     
                     ret_html+=" </ul>"
                     + "</div>";
             
                
            
        }
        
        return  ret_html;
    }
    
    
    
    
}
