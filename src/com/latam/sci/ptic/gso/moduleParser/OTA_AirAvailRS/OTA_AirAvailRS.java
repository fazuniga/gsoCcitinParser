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
    private List<OTA_AirAvailRSElement> ota_AirAvailRS = null;
    
    public List<OTA_AirAvailRSElement> getOta_AirAvailRS() {
        return ota_AirAvailRS;
    }

    public void setOta_AirAvailRS(List<OTA_AirAvailRSElement> ota_AirAvailRS) {
        this.ota_AirAvailRS = ota_AirAvailRS;
    }
        
    public OTA_AirAvailRS() {}
}
