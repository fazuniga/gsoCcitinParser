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
public class RTDPAvailabilityRS_Leg {
    
    private String AirlineCode, FlightNumber, RTDPActive;
    private String ArrivalDateAdj, ArrivalTime, DepartureDate, DepartureTime;
    private String Origin, Destination;
    private String LegCabins, FareCabins;

    public RTDPAvailabilityRS_Leg() {
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

    public String getRTDPActive() {
        return RTDPActive;
    }

    public void setRTDPActive(String RTDPActive) {
        this.RTDPActive = RTDPActive;
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

    public String getLegCabins() {
        return LegCabins;
    }

    public void setLegCabins(String LegCabins) {
        this.LegCabins = LegCabins;
    }

    public String getFareCabins() {
        return FareCabins;
    }

    public void setFareCabins(String FareCabins) {
        this.FareCabins = FareCabins;
    }

    
    
}
