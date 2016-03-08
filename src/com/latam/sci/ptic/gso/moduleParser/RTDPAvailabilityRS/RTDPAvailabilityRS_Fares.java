/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRS;

/**
 *
 * @author 641515
 */
public class RTDPAvailabilityRS_Fares {
    
    private String ClsCode;
    private int FareValue, AdjustedFareValue, BidPrice;

    public RTDPAvailabilityRS_Fares() {
    }

    public String getClsCode() {
        return ClsCode;
    }

    public void setClsCode(String ClsCode) {
        this.ClsCode = ClsCode;
    }

    public int getFareValue() {
        return FareValue;
    }

    public void setFareValue(int FareValue) {
        this.FareValue = FareValue;
    }

    public int getAdjustedFareValue() {
        return AdjustedFareValue;
    }

    public void setAdjustedFareValue(int AdjustedFareValue) {
        this.AdjustedFareValue = AdjustedFareValue;
    }

    public int getBidPrice() {
        return BidPrice;
    }

    public void setBidPrice(int BidPrice) {
        this.BidPrice = BidPrice;
    }
    
    
}
