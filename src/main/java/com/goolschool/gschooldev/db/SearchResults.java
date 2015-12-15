/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.db;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Rajeev
 */

@Named
@RequestScoped
public class SearchResults {
    
    private String school_code;
    private String school_name ;
    private int school_type;
    private String location ;
    private String city ;
    private String est_year;
    private String medium;
    private String rating;
    private String board;
    private String description;
    private String school_logo;
    private String school_facilities;
    private String school_contact;
    private String school_email;
    private String school_website;

    public int getSchool_type() {
        return school_type;
    }

    public void setSchool_type(int school_type) {
        this.school_type = school_type;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
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

    public String getSchool_facilities() {
        return school_facilities;
    }

    public void setSchool_facilities(String school_facilities) {
        this.school_facilities = school_facilities;
    }
    
    
    
    
}
