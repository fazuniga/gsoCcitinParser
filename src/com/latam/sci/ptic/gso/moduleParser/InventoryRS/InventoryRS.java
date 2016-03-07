/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.InventoryRS;

import java.util.List;

/**
 *
 * @author 641515
 */
public class InventoryRS {
    private String FlightCarrier, FlightNumber, FlightDate;
    
    private List<InventoryRS_FlightSegment> FlightSegments;
    private List<InventoryRS_FlightLeg> FlightLegs;
    
    public InventoryRS() {}
    
    public String getFlightCarrier() {
        return FlightCarrier;
    }

    public void setFlightCarrier(String FlightCarrier) {
        this.FlightCarrier = FlightCarrier;
    }

    public String getFlightNumber() {
        return FlightNumber;
    }

    public void setFlightNumber(String FlightNumber) {
        this.FlightNumber = FlightNumber;
    }

    public String getFlightDate() {
        return FlightDate;
    }

    public void setFlightDate(String FlightDate) {
        this.FlightDate = FlightDate;
    }

    public List<InventoryRS_FlightSegment> getFlightSegments() {
        return FlightSegments;
    }

    public void setFlightSegments(List<InventoryRS_FlightSegment> FlightSegments) {
        this.FlightSegments = FlightSegments;
    }

    public List<InventoryRS_FlightLeg> getFlightLegs() {
        return FlightLegs;
    }

    public void setFlightLegs(List<InventoryRS_FlightLeg> FlightLegs) {
        this.FlightLegs = FlightLegs;
    }
}
