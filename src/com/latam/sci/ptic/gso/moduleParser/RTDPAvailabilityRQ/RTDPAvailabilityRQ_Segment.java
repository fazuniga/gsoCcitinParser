/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRQ;

/**
 *
 * @author fazuniga
 */
public class RTDPAvailabilityRQ_Segment {
    
    private String Action, AirlineCode, DepartureDate, Origin, Destination;
    private int FlightNumber;

    public RTDPAvailabilityRQ_Segment() { }

    public String getAction() {
        return Action;
    }

    public void setAction(String Action) {
        this.Action = Action;
    }

    public String getAirlineCode() {
        return AirlineCode;
    }

    public void setAirlineCode(String AirlineCode) {
        this.AirlineCode = AirlineCode;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String DepartureDate) {
        this.DepartureDate = DepartureDate;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String Origin) {
        this.Origin = Origin;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public int getFlightNumber() {
        return FlightNumber;
    }

    public void setFlightNumber(int FlightNumber) {
        this.FlightNumber = FlightNumber;
    }
    
    
}
