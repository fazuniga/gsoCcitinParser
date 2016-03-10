/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS;

import java.util.List;

/**
 *
 * @author 641515
 */
public class OTA_AirAvailRS_OriginDestinationOption {
    
    private List<OTA_AirAvailRS_FlightSegment> FlightSegments;
    private String OriginDestinationRPH;

    public OTA_AirAvailRS_OriginDestinationOption() { }

    public List<OTA_AirAvailRS_FlightSegment> getOTA_AirAvailRSFlightSegments() {
        return FlightSegments;
    }

    public void setOTA_AirAvailRSFlightSegments(List<OTA_AirAvailRS_FlightSegment> FlightSegments) {
        this.FlightSegments = FlightSegments;
    }

    public String getOriginDestinationRPH() {
        return OriginDestinationRPH;
    }

    public void setOriginDestinationRPH(String OriginDestinationRPH) {
        this.OriginDestinationRPH = OriginDestinationRPH;
    }
}
