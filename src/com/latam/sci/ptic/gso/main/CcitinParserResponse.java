/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.main;

import java.util.List;
import com.latam.sci.ptic.gso.moduleParser.CcitinRQ.CcitinRQ;
import com.latam.sci.ptic.gso.moduleParser.CcitinRS.CcitinRS;
import com.latam.sci.ptic.gso.moduleParser.InventoryRQ.InventoryRQ;
import com.latam.sci.ptic.gso.moduleParser.InventoryRS.InventoryRS;
import com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRQ.OTA_AirAvailRQ;
import com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRS;
import com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRQ.RTDPAvailabilityRQ;
import com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRS.RTDPAvailabilityRS;
/**
 *
 * @author 641515
 */
public class CcitinParserResponse {
    private String FileName;
    
    public CcitinRQ ccitinRQ;
    public CcitinRS ccitinRS;
    
    public List<OTA_AirAvailRQ> OTA_AirAvailRQList;
    public List<OTA_AirAvailRS> OTA_AirAvailRSList;
    
    public List<InventoryRQ> InventoryRQList;
    public List<InventoryRS> InventoryRSList;
    
    public List<RTDPAvailabilityRQ> rtdpAvailabilityRQ;
    public List<RTDPAvailabilityRS> rtdpAvailabilityRS;

    public CcitinParserResponse() { }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public CcitinRQ getCcitinRQ() {
        return ccitinRQ;
    }

    public void setCcitinRQ(CcitinRQ ccitinRQ) {
        this.ccitinRQ = ccitinRQ;
    }

    public CcitinRS getCcitinRS() {
        return ccitinRS;
    }

    public void setCcitinRS(CcitinRS ccitinRS) {
        this.ccitinRS = ccitinRS;
    }

    public List<OTA_AirAvailRQ> getOTA_AirAvailRQList() {
        return OTA_AirAvailRQList;
    }

    public void setOTA_AirAvailRQList(List<OTA_AirAvailRQ> OTA_AirAvailRQList) {
        this.OTA_AirAvailRQList = OTA_AirAvailRQList;
    }

    public List<OTA_AirAvailRS> getOTA_AirAvailRSList() {
        return OTA_AirAvailRSList;
    }

    public void setOTA_AirAvailRSList(List<OTA_AirAvailRS> OTA_AirAvailRSList) {
        this.OTA_AirAvailRSList = OTA_AirAvailRSList;
    }

    public List<InventoryRQ> getInventoryRQList() {
        return InventoryRQList;
    }

    public void setInventoryRQList(List<InventoryRQ> InventoryRQList) {
        this.InventoryRQList = InventoryRQList;
    }

    public List<InventoryRS> getInventoryRSList() {
        return InventoryRSList;
    }

    public void setInventoryRSList(List<InventoryRS> InventoryRSList) {
        this.InventoryRSList = InventoryRSList;
    }

    public List<RTDPAvailabilityRQ> getRtdpAvailabilityRQ() {
        return rtdpAvailabilityRQ;
    }

    public void setRtdpAvailabilityRQ(List<RTDPAvailabilityRQ> rtdpAvailabilityRQ) {
        this.rtdpAvailabilityRQ = rtdpAvailabilityRQ;
    }

    public List<RTDPAvailabilityRS> getRtdpAvailabilityRS() {
        return rtdpAvailabilityRS;
    }

    public void setRtdpAvailabilityRS(List<RTDPAvailabilityRS> rtdpAvailabilityRS) {
        this.rtdpAvailabilityRS = rtdpAvailabilityRS;
    }
    
    
}
