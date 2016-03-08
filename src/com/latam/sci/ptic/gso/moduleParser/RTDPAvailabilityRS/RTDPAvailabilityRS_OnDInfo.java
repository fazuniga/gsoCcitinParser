/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRS;

import java.util.List;

/**
 *
 * @author 641515
 */
public class RTDPAvailabilityRS_OnDInfo {
    
    private String Origin, Destination;
    private List<Integer> SegIndex;
    private List<RTDPAvailabilityRS_Fares> Fares;
    
    public RTDPAvailabilityRS_OnDInfo() {}

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

    public List<Integer> getSegIndex() {
        return SegIndex;
    }

    public void setSegIndex(List<Integer> SegIndex) {
        this.SegIndex = SegIndex;
    }

    public List<RTDPAvailabilityRS_Fares> getFares() {
        return Fares;
    }

    public void setFares(List<RTDPAvailabilityRS_Fares> Fares) {
        this.Fares = Fares;
    }
    
    
}
