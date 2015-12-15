/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goolschool.gschooldev.util;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Rajeev
 */


@Named
@RequestScoped
public class SchoolFacilities {
    
    private String facility;
    private int status;
    private String val;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
    

    
    
    
}
