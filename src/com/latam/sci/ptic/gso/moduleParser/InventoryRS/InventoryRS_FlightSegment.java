/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.InventoryRS;

import com.latam.sci.ptic.gso.auxiliar.SeatsClass;
import java.util.List;

/**
 *
 * @author 641515
 */
public class InventoryRS_FlightSegment {
    
    private String SegOrgn, SegDstn, SegDate, SegFltNum;
    private int NumLegsThrough;
    private List<SeatsClass> SeatsSold;
    private List<SeatsClass> MaxSeats;
    private List<SeatsClass> GroupCounts;
    
    private String SegDepTime, SegDepDateAdj;
    private String SegArrTime, SegArrDateAdj;
    private String FareClasses;
    
    public InventoryRS_FlightSegment() { }

    public List<SeatsClass> getMaxSeats() {
        return MaxSeats;
    }

    public void setMaxSeats(List<SeatsClass> MaxSeats) {
        this.MaxSeats = MaxSeats;
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

    public String getSegDate() {
        return SegDate;
    }

    public void setSegDate(String SegDate) {
        this.SegDate = SegDate;
    }

    public String getSegFltNum() {
        return SegFltNum;
    }

    public void setSegFltNum(String SegFltNum) {
        this.SegFltNum = SegFltNum;
    }

    public int getNumLegsThrough() {
        return NumLegsThrough;
    }

    public void setNumLegsThrough(int NumLegsThrough) {
        this.NumLegsThrough = NumLegsThrough;
    }

    public List<SeatsClass> getSeatsSold() {
        return SeatsSold;
    }

    public void setSeatsSold(List<SeatsClass> SeatsSold) {
        this.SeatsSold = SeatsSold;
    }

    public List<SeatsClass> getGroupCounts() {
        return GroupCounts;
    }

    public void setGroupCounts(List<SeatsClass> GroupCounts) {
        this.GroupCounts = GroupCounts;
    }

    public String getSegDepTime() {
        return SegDepTime;
    }

    public void setSegDepTime(String SegDepTime) {
        this.SegDepTime = SegDepTime;
    }

    public String getSegDepDateAdj() {
        return SegDepDateAdj;
    }

    public void setSegDepDateAdj(String SegDepDateAdj) {
        this.SegDepDateAdj = SegDepDateAdj;
    }

    public String getSegArrTime() {
        return SegArrTime;
    }

    public void setSegArrTime(String SegArrTime) {
        this.SegArrTime = SegArrTime;
    }

    public String getSegArrDateAdj() {
        return SegArrDateAdj;
    }

    public void setSegArrDateAdj(String SegArrDateAdj) {
        this.SegArrDateAdj = SegArrDateAdj;
    }

    public String getFareClasses() {
        return FareClasses;
    }

    public void setFareClasses(String FareClasses) {
        this.FareClasses = FareClasses;
    }
}
