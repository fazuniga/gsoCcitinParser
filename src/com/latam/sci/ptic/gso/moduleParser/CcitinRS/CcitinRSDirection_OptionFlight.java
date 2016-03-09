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
public class CcitinRSDirection_OptionFlight {
    
    private int EFT, sequenceNumber;
    private String AircraftType;
    private String arrivalDate, arrivalTime, departureDate, departureTime;
    private String carrier, flightNumber;
    private String Origin, Destination;
    
    private List<CcitinRSDirection_OptionFlight_Leg> Legs;
    private List<CcitinRSDirection_OptionFlight_Segment> Segments;

    public CcitinRSDirection_OptionFlight() {
    }

    public int getEFT() {
        return EFT;
    }

    public void setEFT(int EFT) {
        this.EFT = EFT;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getAircraftType() {
        return AircraftType;
    }

    public void setAircraftType(String AircraftType) {
        this.AircraftType = AircraftType;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
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

    public List<CcitinRSDirection_OptionFlight_Leg> getLegs() {
        return Legs;
    }

    public void setLegs(List<CcitinRSDirection_OptionFlight_Leg> Legs) {
        this.Legs = Legs;
    }

    public List<CcitinRSDirection_OptionFlight_Segment> getSegments() {
        return Segments;
    }

    public void setSegments(List<CcitinRSDirection_OptionFlight_Segment> Segments) {
        this.Segments = Segments;
    }
}
