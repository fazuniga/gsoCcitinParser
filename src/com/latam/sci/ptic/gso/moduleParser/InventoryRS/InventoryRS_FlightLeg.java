/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.InventoryRS;

import com.latam.sci.ptic.gso.auxiliar.CabinCapacity;
import com.latam.sci.ptic.gso.auxiliar.SeatsClass;
import com.latam.sci.ptic.gso.auxiliar.CabinNesting;
import java.util.List;

/**
 *
 * @author 641515
 */
public class InventoryRS_FlightLeg {

    private String LegOrgn, LegDstn, LegDate;
    private String LegDepTime, LegDepDateAdj;
    private String LegArrTime, LegArrDateAdj;
    private String OperatingCrrCode, OperatingFltNum;
    private Boolean BidPriceFlag, CodeshareFlag;
    
    private List<SeatsClass> SeatsSold;
    private List<SeatsClass> LegAuthorization;
    private List<CabinCapacity> CabinCapacities;
    private List<CabinNesting> CabinNesting;
    
    public InventoryRS_FlightLeg() {}
    
    public String getLegOrgn() {
        return LegOrgn;
    }

    public void setLegOrgn(String LegOrgn) {
        this.LegOrgn = LegOrgn;
    }

    public String getLegDstn() {
        return LegDstn;
    }

    public void setLegDstn(String LegDstn) {
        this.LegDstn = LegDstn;
    }

    public String getLegDate() {
        return LegDate;
    }

    public void setLegDate(String LegDate) {
        this.LegDate = LegDate;
    }

    public String getLegDepTime() {
        return LegDepTime;
    }

    public void setLegDepTime(String LegDepTime) {
        this.LegDepTime = LegDepTime;
    }

    public String getLegDepDateAdj() {
        return LegDepDateAdj;
    }

    public void setLegDepDateAdj(String LegDepDateAdj) {
        this.LegDepDateAdj = LegDepDateAdj;
    }

    public String getLegArrTime() {
        return LegArrTime;
    }

    public void setLegArrTime(String LegArrTime) {
        this.LegArrTime = LegArrTime;
    }

    public String getLegArrDateAdj() {
        return LegArrDateAdj;
    }

    public void setLegArrDateAdj(String LegArrDateAdj) {
        this.LegArrDateAdj = LegArrDateAdj;
    }

    public String getOperatingCrrCode() {
        return OperatingCrrCode;
    }

    public void setOperatingCrrCode(String OperatingCrrCode) {
        this.OperatingCrrCode = OperatingCrrCode;
    }

    public String getOperatingFltNum() {
        return OperatingFltNum;
    }

    public void setOperatingFltNum(String OperatingFltNum) {
        this.OperatingFltNum = OperatingFltNum;
    }

    public Boolean getBidPriceFlag() {
        return BidPriceFlag;
    }

    public void setBidPriceFlag(Boolean BidPriceFlag) {
        this.BidPriceFlag = BidPriceFlag;
    }

    public Boolean getCodeshareFlag() {
        return CodeshareFlag;
    }

    public void setCodeshareFlag(Boolean CodeshareFlag) {
        this.CodeshareFlag = CodeshareFlag;
    }

    public List<SeatsClass> getSeatsSold() {
        return SeatsSold;
    }

    public void setSeatsSold(List<SeatsClass> SeatsSold) {
        this.SeatsSold = SeatsSold;
    }

    public List<SeatsClass> getLegAuthorization() {
        return LegAuthorization;
    }

    public void setLegAuthorization(List<SeatsClass> LegAuthorization) {
        this.LegAuthorization = LegAuthorization;
    }

    public List<CabinCapacity> getCabinCapacities() {
        return CabinCapacities;
    }

    public void setCabinCapacities(List<CabinCapacity> CabinCapacities) {
        this.CabinCapacities = CabinCapacities;
    }

    public List<CabinNesting> getCabinNesting() {
        return CabinNesting;
    }

    public void setCabinNesting(List<CabinNesting> CabinNesting) {
        this.CabinNesting = CabinNesting;
    }
    
}
