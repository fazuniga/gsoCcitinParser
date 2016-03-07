/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRQ;

/**
 *
 * @author 641515
 */
public class OTA_AirAvailRQ {
    private String Charters, DirectOnly, ExcludeCodeshares;
    private String MinDeparture, MaxDeparture;
    private String DepDate;
    private String Origin, Destination;
    private String MoreAvailability = "false";
    
    public OTA_AirAvailRQ() {}
    
    public String getCharters() {
        return Charters;
    }

    public void setCharters(String Charters) {
        this.Charters = Charters;
    }

    public String getDirectOnly() {
        return DirectOnly;
    }

    public void setDirectOnly(String DirectOnly) {
        this.DirectOnly = DirectOnly;
    }

    public String getExcludeCodeshares() {
        return ExcludeCodeshares;
    }

    public void setExcludeCodeshares(String ExcludeCodeshares) {
        this.ExcludeCodeshares = ExcludeCodeshares;
    }

    public String getMinDeparture() {
        return MinDeparture;
    }

    public void setMinDeparture(String MinDeparture) {
        this.MinDeparture = MinDeparture;
    }

    public String getMaxDeparture() {
        return MaxDeparture;
    }

    public void setMaxDeparture(String MaxDeparture) {
        this.MaxDeparture = MaxDeparture;
    }

    public String getDepDate() {
        return DepDate;
    }

    public void setDepDate(String DepDate) {
        this.DepDate = DepDate;
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

    public String getMoreAvailability() {
        return MoreAvailability;
    }

    public void setMoreAvailability(String MoreAvailability) {
        this.MoreAvailability = MoreAvailability;
    }
    
    public Boolean isComplete()
    {
        if (this.Charters != null && this.DirectOnly != null && this.ExcludeCodeshares != null &&
            this.MinDeparture != null && this.MaxDeparture != null &&
            this.DepDate != null && this.Origin != null && this.Destination != null &&     
            this.MoreAvailability != null) {
            return true;
        } else {
            return false;
        }
    }
}
