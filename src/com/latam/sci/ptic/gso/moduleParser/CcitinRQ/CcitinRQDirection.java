/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.CcitinRQ;

/**
 *
 * @author 641515
 */
public class CcitinRQDirection {
    private String DirectionID;
    private String Origin, Destination, cityPOS, countryPOS;
    private String directFlights, allowOAL;
    private String startDate, endDate, frequency;
    private String startTime, endTime;
    private String orientation;

    public CcitinRQDirection() {
    }

    public String getDirectionID() {
        return DirectionID;
    }

    public void setDirectionID(String DirectionID) {
        this.DirectionID = DirectionID;
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

    public String getCityPOS() {
        return cityPOS;
    }

    public void setCityPOS(String cityPOS) {
        this.cityPOS = cityPOS;
    }

    public String getCountryPOS() {
        return countryPOS;
    }

    public void setCountryPOS(String countryPOS) {
        this.countryPOS = countryPOS;
    }

    public String getDirectFlights() {
        return directFlights;
    }

    public void setDirectFlights(String directFlights) {
        this.directFlights = directFlights;
    }

    public String getAllowOAL() {
        return allowOAL;
    }

    public void setAllowOAL(String allowOAL) {
        this.allowOAL = allowOAL;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
    
    public Boolean isComplete()
    {
        if (this.DirectionID != null && this.Origin != null && this.Destination != null &&
            this.cityPOS != null && this.countryPOS != null &&
            this.directFlights != null && this.allowOAL != null &&
            this.startDate != null && this.endDate != null && this.frequency != null &&
            this.startTime != null && this.endTime != null && this.orientation != null) {
            return true;
        } else {
            return false;
        }
    }
}
