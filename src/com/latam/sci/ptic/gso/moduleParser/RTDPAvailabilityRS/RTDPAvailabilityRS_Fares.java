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
    private Integer FareValue, AdjustedFareValue, BidPrice;

    public RTDPAvailabilityRS_Fares() {
    }

    public String getClsCode() {
        return ClsCode;
    }

    public void setClsCode(String ClsCode) {
        this.ClsCode = ClsCode;
    }

    public Integer getFareValue() {
        return FareValue;
    }

    public void setFareValue(Integer FareValue) {
        this.FareValue = FareValue;
    }

    public Integer getAdjustedFareValue() {
        return AdjustedFareValue;
    }

    public void setAdjustedFareValue(Integer AdjustedFareValue) {
        this.AdjustedFareValue = AdjustedFareValue;
    }

    public Integer getBidPrice() {
        return BidPrice;
    }

    public void setBidPrice(Integer BidPrice) {
        this.BidPrice = BidPrice;
    }
    
    
}
