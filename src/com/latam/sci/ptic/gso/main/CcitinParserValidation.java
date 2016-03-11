/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.main;

import com.latam.sci.ptic.gso.auxiliar.Constants;
import com.latam.sci.ptic.gso.auxiliar.SeatsClass;
import com.latam.sci.ptic.gso.moduleParser.CcitinRS.CcitinRSDirection_OptionFlight_Seg_Cmp_Class;
import com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRS;
import com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRS_FlightSegment;
import com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRS_OriginDestinationOption;
import com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRS.RTDPAvailabilityRS;
import com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRS.RTDPAvailabilityRS_OnDInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author 641515
 */
public class CcitinParserValidation {

    public CcitinParserValidation() { }
    
    public void validateResponses(List<CcitinParserResponse> cprList) {
        
        System.out.println("----------------------------------------");
        System.out.println("############ Validation ############");
        System.out.println("----------------------------------------");
        
        for (CcitinParserResponse cpr : cprList)
        {
            cpr.ccitinRQ.printCcitinRQ();
            
            // OTA_AirAvailRS
            for (OTA_AirAvailRS otaRS : cpr.getOTA_AirAvailRSList())
            {
                // Loop through all options obtained
                System.out.println();
                for (OTA_AirAvailRS_OriginDestinationOption option : otaRS.getOptions())
                {
                    System.out.println("[Option # " + option.getOriginDestinationRPH() + "]");
                    
                    // Loop through flight segments obtained
                    for (OTA_AirAvailRS_FlightSegment segment : option.getOTA_AirAvailRSFlightSegments())
                    {
                        System.out.println(" [Segmento # " + segment.getFlightRPH() + "]: " 
                                + segment.getMarketingAirlineCode() + " - " + FormatFltNum(Integer.parseInt(segment.getMarketingFlightNumber())) + " - "
                                + segment.getOriginLocation() + " - " + segment.getDestinationLocation()
                                + segment.getDepartureDate() + " - " + segment.getArrivalDate()
                                + " - Online: " + IsOnlineCarrier(segment.getMarketingAirlineCode()).toString()
                         );
                    }
                }
            }
            
            for (RTDPAvailabilityRS rtdpRS : cpr.rtdpAvailabilityRS)
            {
                for (RTDPAvailabilityRS_OnDInfo OnDInfo : rtdpRS.getOnDInfos())
                {
                    System.out.println("## O&D: " + OnDInfo.getOrigin() + " / " + OnDInfo.getDestination());
                    
                    Boolean RTDPControlled = false;
                    for (Integer SegIndex : OnDInfo.getSegIndex())
                    {
                        String segRTDPActive = rtdpRS.getSegments().get(SegIndex - 1).getRTDPActive();
                        Boolean RTDPActive = (segRTDPActive == "true") ? true : false;
                        RTDPControlled = RTDPControlled && RTDPActive;
                    }
                    
                    System.out.println("## Disponibilidad: " + ((RTDPControlled) ? "BP" : "AU"));
                    
                    // Choose the right availability
                    List<List<SeatsClass>> Availability = new ArrayList<>();
                    for (Integer SegIndex : OnDInfo.getSegIndex())
                    {
                        if (RTDPControlled) {
                            Availability.add(rtdpRS.getSegments().get(SegIndex - 1).getRTDPAvail());
                        } else {
                            Availability.add(rtdpRS.getSegments().get(SegIndex - 1).getSeatsAvail());
                        }
                    }
                    
                    // Final availability
                    List<CcitinRSDirection_OptionFlight_Seg_Cmp_Class> Classes = cpr.ccitinRS
                        .getDirections().get(0)
                        .getOptions().get(0)
                        .getFlights().get(0)
                        .getSegments().get(0)
                        .getCompartments().get(0)
                        .getClasses();
                    
                    for (CcitinRSDirection_OptionFlight_Seg_Cmp_Class Class : Classes)
                    {
                        System.out.println("Clase: " + Class.getClsCode() + " - Availability: " + Class.getOdAvailability());
                    }
                    
                    String a = "0";
                }
            }
        }
    }
    
    public Boolean IsOnlineCarrier(String CrrCode) {
        return Arrays.asList(Constants.LATAM_CARRIERS).contains(CrrCode);
    }
    
    public String FormatFltNum(int FltNum) {
        return String.format("%0" + Constants.FLTNUM_DIGITS + "d", FltNum);
    }
}
