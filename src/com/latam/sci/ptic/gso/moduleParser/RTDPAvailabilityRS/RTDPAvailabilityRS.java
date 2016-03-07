/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRS;

import java.util.List;

/**
 *
 * @author fazuniga
 */
public class RTDPAvailabilityRS {
    private String AirlineGDS, POSCity, POSCountry;
    private int NumberOfSeats;
    
    private List<RTDPAvailabilityRS_Segment> Segments;
    private String Origin, Destination;
    
    
    public RTDPAvailabilityRS() {}

    public String getAirlineGDS() {
        return AirlineGDS;
    }

    public void setAirlineGDS(String AirlineGDS) {
        this.AirlineGDS = AirlineGDS;
    }

    public String getPOSCity() {
        return POSCity;
    }

    public void setPOSCity(String POSCity) {
        this.POSCity = POSCity;
    }

    public String getPOSCountry() {
        return POSCountry;
    }

    public void setPOSCountry(String POSCountry) {
        this.POSCountry = POSCountry;
    }

    public List<RTDPAvailabilityRS_Segment> getSegments() {
        return Segments;
    }

    public void setSegments(List<RTDPAvailabilityRS_Segment> Segments) {
        this.Segments = Segments;
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

    public int getNumberOfSeats() {
        return NumberOfSeats;
    }

    public void setNumberOfSeats(int NumberOfSeats) {
        this.NumberOfSeats = NumberOfSeats;
    }
    
    
}
