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
public class OTA_AirAvailRSElement {
    
    public static String OTA_AirAvailRS_OriginDestinationOption_start = "^\\s*<OriginDestinationOption RPH=\"(?<OriginDestinationRPH>\\d+)\".*$";
    public static String OTA_AirAvailRS_OriginDestinationOption_end = "^\\s*</OriginDestinationOption>.*$";
    
    private List<OTA_AirAvailRSFlightSegment> OTA_AirAvailRSFlightSegments;
    private String OriginDestinationRPH;
    
    public OTA_AirAvailRSElement() {}
    
    
    public List<OTA_AirAvailRSFlightSegment> getOTA_AirAvailRSFlightSegments() {
        return OTA_AirAvailRSFlightSegments;
    }

    public void setOTA_AirAvailRSFlightSegments(List<OTA_AirAvailRSFlightSegment> OTA_AirAvailRSFlightSegments) {
        this.OTA_AirAvailRSFlightSegments = OTA_AirAvailRSFlightSegments;
    }

    public String getOriginDestinationRPH() {
        return OriginDestinationRPH;
    }

    public void setOriginDestinationRPH(String OriginDestinationRPH) {
        this.OriginDestinationRPH = OriginDestinationRPH;
    }
}
