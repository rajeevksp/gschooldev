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
public class StringList {
    
    private String str;
    private String trail;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getTrail() {
        return trail;
    }

    public void setTrail(String trail) {
        this.trail = trail;
    }
    
    
    
}
