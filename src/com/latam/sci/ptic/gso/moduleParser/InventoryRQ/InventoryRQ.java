/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.InventoryRQ;

/**
 *
 * @author 641515
 */
public class InventoryRQ {
    private String FlightCarrier, FlightNumber, FlightDate;
    
    public InventoryRQ() {
    }
    
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
}
