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
public class OTA_AirAvailRS {
    
    private List<OTA_AirAvailRS_OriginDestinationOption> Options;
        
    public OTA_AirAvailRS() {}

    public List<OTA_AirAvailRS_OriginDestinationOption> getOptions() {
        return Options;
    }

    public void setOptions(List<OTA_AirAvailRS_OriginDestinationOption> Options) {
        this.Options = Options;
    }
}
