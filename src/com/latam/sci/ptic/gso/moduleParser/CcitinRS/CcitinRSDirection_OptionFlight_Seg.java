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
public class CcitinRSDirection_OptionFlight_Seg {
    private int allowBooking, allowGroup, allowSale, allowWaitlist;
    private int SegSequenceNumber;
    private String SegOrgn, SegDstn;
    
    private List<CcitinRSDirection_OptionFlight_Seg_Cmp> Compartments;

    public CcitinRSDirection_OptionFlight_Seg() {
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

    public int getSegSequenceNumber() {
        return SegSequenceNumber;
    }

    public void setSegSequenceNumber(int SegSequenceNumber) {
        this.SegSequenceNumber = SegSequenceNumber;
    }

    public String getSegOrgn() {
        return SegOrgn;
    }

    public void setSegOrgn(String SegOrgn) {
        this.SegOrgn = SegOrgn;
    }

    public String getSegDstn() {
        return SegDstn;
    }

    public void setSegDstn(String SegDstn) {
        this.SegDstn = SegDstn;
    }

    public List<CcitinRSDirection_OptionFlight_Seg_Cmp> getCompartments() {
        return Compartments;
    }

    public void setCompartments(List<CcitinRSDirection_OptionFlight_Seg_Cmp> Compartments) {
        this.Compartments = Compartments;
    }
}
