/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.tblWeapon;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Dell
 */
public class TblWeaponDTO implements Serializable{
    
    String amourId, description, classification, defense;
    Date timeOfCreate;
    boolean status;

    public TblWeaponDTO(String amourId, String description, String classification, String defense, Date timeOfCreate, boolean status) {
        this.amourId = amourId;
        this.description = description;
        this.classification = classification;
        this.defense = defense;
        this.timeOfCreate = timeOfCreate;
        this.status = status;
    }

    public String getAmourId() {
        return amourId;
    }

    public void setAmourId(String amourId) {
        this.amourId = amourId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDefense() {
        return defense;
    }

    public void setDefense(String defense) {
        this.defense = defense;
    }

    public Date getTimeOfCreate() {
        return timeOfCreate;
    }

    public void setTimeOfCreate(Date timeOfCreate) {
        this.timeOfCreate = timeOfCreate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
