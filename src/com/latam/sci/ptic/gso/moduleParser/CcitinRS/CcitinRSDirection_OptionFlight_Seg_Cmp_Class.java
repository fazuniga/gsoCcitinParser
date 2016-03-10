/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.CcitinRS;

/**
 *
 * @author 641515
 */
public class CcitinRSDirection_OptionFlight_Seg_Cmp_Class {
    private String ClsCode;
    private int allowBooking, allowGroup, allowSale, allowWaitList, groupBooked, totalBooked;
    private int limit, odAvailability;

    public CcitinRSDirection_OptionFlight_Seg_Cmp_Class() {
    }

    public String getClsCode() {
        return ClsCode;
    }

    public void setClsCode(String ClsCode) {
        this.ClsCode = ClsCode;
    }

    public int getAllowBooking() {
        return allowBooking;
    }

    public void setAllowBooking(int allowBooking) {
        this.allowBooking = allowBooking;
    }

    public int getAllowGroup() {
        return allowGroup;
    }

    public void setAllowGroup(int allowGroup) {
        this.allowGroup = allowGroup;
    }

    public int getAllowSale() {
        return allowSale;
    }

    public void setAllowSale(int allowSale) {
        this.allowSale = allowSale;
    }

    public int getAllowWaitList() {
        return allowWaitList;
    }

    public void setAllowWaitList(int allowWaitList) {
        this.allowWaitList = allowWaitList;
    }

    public int getGroupBooked() {
        return groupBooked;
    }

    public void setGroupBooked(int groupBooked) {
        this.groupBooked = groupBooked;
    }

    public int getTotalBooked() {
        return totalBooked;
    }

    public void setTotalBooked(int totalBooked) {
        this.totalBooked = totalBooked;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOdAvailability() {
        return odAvailability;
    }

    public void setOdAvailability(int odAvailability) {
        this.odAvailability = odAvailability;
    }
}
