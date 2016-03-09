/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.CcitinRS;

import java.util.List;

/**
 *
 * @author 641515
 */
public class CcitinRSDirection_OptionFlight_Leg_Cmp {
    
    private int AdjustedCapacity, Capacity, groupBooked, odFactor, totalAU, totalBooked;
    private String CmpCode;
    private List<CcitinRSDirection_OptionFlight_Leg_Cmp_Class> Classes;

    public CcitinRSDirection_OptionFlight_Leg_Cmp() {
    }

    public int getAdjustedCapacity() {
        return AdjustedCapacity;
    }

    public void setAdjustedCapacity(int AdjustedCapacity) {
        this.AdjustedCapacity = AdjustedCapacity;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public int getGroupBooked() {
        return groupBooked;
    }

    public void setGroupBooked(int groupBooked) {
        this.groupBooked = groupBooked;
    }

    public int getOdFactor() {
        return odFactor;
    }

    public void setOdFactor(int odFactor) {
        this.odFactor = odFactor;
    }

    public int getTotalAU() {
        return totalAU;
    }

    public void setTotalAU(int totalAU) {
        this.totalAU = totalAU;
    }

    public int getTotalBooked() {
        return totalBooked;
    }

    public void setTotalBooked(int totalBooked) {
        this.totalBooked = totalBooked;
    }

    public String getCmpCode() {
        return CmpCode;
    }

    public void setCmpCode(String CmpCode) {
        this.CmpCode = CmpCode;
    }

    public List<CcitinRSDirection_OptionFlight_Leg_Cmp_Class> getClasses() {
        return Classes;
    }

    public void setClasses(List<CcitinRSDirection_OptionFlight_Leg_Cmp_Class> Classes) {
        this.Classes = Classes;
    }
}
