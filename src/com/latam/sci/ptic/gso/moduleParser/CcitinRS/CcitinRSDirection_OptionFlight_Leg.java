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
public class CcitinRSDirection_OptionFlight_Leg {
    private int allowBooking, allowGroup, allowSale, allowWaitlist;
    private int dateOffset, LegSequenceNumber;
    private String destination;
    
    private List<CcitinRSDirection_OptionFlight_Leg_Cmp> Compartments;

    public CcitinRSDirection_OptionFlight_Leg() {
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

    public int getAllowWaitlist() {
        return allowWaitlist;
    }

    public void setAllowWaitlist(int allowWaitlist) {
        this.allowWaitlist = allowWaitlist;
    }

    public int getDateOffset() {
        return dateOffset;
    }

    public void setDateOffset(int dateOffset) {
        this.dateOffset = dateOffset;
    }

    public int getLegSequenceNumber() {
        return LegSequenceNumber;
    }

    public void setLegSequenceNumber(int LegSequenceNumber) {
        this.LegSequenceNumber = LegSequenceNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<CcitinRSDirection_OptionFlight_Leg_Cmp> getCompartments() {
        return Compartments;
    }

    public void setCompartments(List<CcitinRSDirection_OptionFlight_Leg_Cmp> Compartments) {
        this.Compartments = Compartments;
    }
}
