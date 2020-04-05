/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practicalexam.student.tblDoctorDTO;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author T.Z.B
 */
public class DoctorDTO implements Serializable {

    private String doctorId;
    private String fullName;
    private String specialization;
    private String phoneNumber;
    private String address;
    private boolean isLeader;

    // Scheduler Information
    private int id;
    private Date shiftDate;
    private Date fromTime;
    private Date dateTime;
    private String description;

    public DoctorDTO() {
    }

    public DoctorDTO(String doctorId, String fullName, String specialization, String phoneNumber, Date shiftDate, Date fromTime, Date dateTime, String description) {
        this.doctorId = doctorId;
        this.fullName = fullName;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.shiftDate = shiftDate;
        this.fromTime = fromTime;
        this.dateTime = dateTime;
        this.description = description;
    }

    public DoctorDTO(String doctorId, String fullName, String specialization, String phoneNumber, String address, boolean isLeader, int id, Date shiftDate, Date fromTime, Date dateTime, String description) {
        this.doctorId = doctorId;
        this.fullName = fullName;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.isLeader = isLeader;
        this.id = id;
        this.shiftDate = shiftDate;
        this.fromTime = fromTime;
        this.dateTime = dateTime;
        this.description = description;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isIsLeader() {
        return isLeader;
    }

    public void setIsLeader(boolean isLeader) {
        this.isLeader = isLeader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
