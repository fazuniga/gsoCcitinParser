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
public class RTDPAvailabilityRS_Segment {
    private String Action;
    private String AirlineCode, FlightNumber;
    private String ArrivalDateAdj, ArrivalTime, DepartureDate, DepartureTime;
    private String Origin, Destination;
    private String FareClasses;
    private int NoOfLegs;
    private String RTDPActive;

    public RTDPAvailabilityRS_Segment() {}
    
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

    public String getFlightNumber() {
        return FlightNumber;
    }

    public void setFlightNumber(String FlightNumber) {
        this.FlightNumber = FlightNumber;
    }

    public String getArrivalDateAdj() {
        return ArrivalDateAdj;
    }

    public void setArrivalDateAdj(String ArrivalDateAdj) {
        this.ArrivalDateAdj = ArrivalDateAdj;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String ArrivalTime) {
        this.ArrivalTime = ArrivalTime;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String DepartureDate) {
        this.DepartureDate = DepartureDate;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(String DepartureTime) {
        this.DepartureTime = DepartureTime;
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

    public String getFareClasses() {
        return FareClasses;
    }

    public void setFareClasses(String FareClasses) {
        this.FareClasses = FareClasses;
    }

    public int getNoOfLegs() {
        return NoOfLegs;
    }

    public void setNoOfLegs(int NoOfLegs) {
        this.NoOfLegs = NoOfLegs;
    }

    public String getRTDPActive() {
        return RTDPActive;
    }

    public void setRTDPActive(String RTDPActive) {
        this.RTDPActive = RTDPActive;
    }
    
    
}
