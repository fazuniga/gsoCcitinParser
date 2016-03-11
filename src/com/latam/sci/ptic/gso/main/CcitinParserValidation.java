/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.main;

import com.latam.sci.ptic.gso.auxiliar.Constants;
import com.latam.sci.ptic.gso.moduleParser.InventoryRQ.InventoryRQ;
import com.latam.sci.ptic.gso.moduleParser.InventoryRS.InventoryRS;
import com.latam.sci.ptic.gso.moduleParser.InventoryRS.InventoryRS_FlightLeg;
import com.latam.sci.ptic.gso.moduleParser.InventoryRS.InventoryRS_FlightSegment;
import com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRS;
import com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRS_FlightSegment;
import com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRS_OriginDestinationOption;
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
            System.out.println("##\tProcesando:\t" + cpr.getFileName());
            
            cpr.ccitinRQ.printCcitinRQ();
            
            System.out.println();
            
            // OTA_AirAvailRS
            int OTA_RS = 0;

            for (OTA_AirAvailRS otaRS : cpr.getOTA_AirAvailRSList())
            {
                OTA_RS++;
                // Loop through all options obtained
                // System.out.println();
                if (otaRS.getOptions() != null)
                {
                    Boolean newOption;
                    for (OTA_AirAvailRS_OriginDestinationOption option : otaRS.getOptions())
                    {   
                        // Loop through flight segments obtained
                        newOption = true;

                        for (OTA_AirAvailRS_FlightSegment segment : option.getOTA_AirAvailRSFlightSegments())
                        {
                            System.out.println("[OTA_AvailRS # " + Constants.FormatRPH(OTA_RS) + "] "
                                    + "[OPTION # " + Constants.FormatRPH(Integer.parseInt(option.getOriginDestinationRPH())) + "] "
                                    + "[SEGMENT # " + Constants.FormatRPH(Integer.parseInt(segment.getFlightRPH())) + "] "
                                    + ((newOption) ? "[*]" : "[ ]") + " "
                                    + segment.getMarketingAirlineCode() + " - " + Constants.FormatFltNum(Integer.parseInt(segment.getMarketingFlightNumber())) + " - "
                                    + segment.getOriginLocation() + " - " + segment.getDestinationLocation() + " - "
                                    + segment.getDepartureDate() + " - " + segment.getArrivalDate() + " "
                                    + ((Constants.IsOnlineCarrier(segment.getMarketingAirlineCode())) ? "[ONLINE]" : "[OFFLINE]")
                            );  
                            newOption = false;
                        }
                    }
                }
            }
            
            // InventoryRQ
            if (cpr.getInventoryRQList() != null)
            {
                System.out.println();
                int invRQ_Number = 0;
                for (InventoryRQ invRQ : cpr.getInventoryRQList())
                {
                    invRQ_Number++;

                    System.out.println("[InventoryRQ # " + Constants.FormatRPH(invRQ_Number) + "] "
                            + invRQ.getFlightCarrier() + " - " + Constants.FormatFltNum(Integer.parseInt(invRQ.getFlightNumber())) + " - "
                            + invRQ.getFlightDate() + " - "
                            + ((Constants.IsOnlineCarrier(invRQ.getFlightCarrier())) ? "[ONLINE]" : "[OFFLINE]")
                    );
                }
            }
            
            // InventoryRS
            if (cpr.getInventoryRSList() != null)
            {
                System.out.println();
                int invRS_Number = 0;
                for (InventoryRS invRS : cpr.getInventoryRSList())
                {
                    invRS_Number++;

                    System.out.println("[InventoryRS # " + Constants.FormatRPH(invRS_Number) + "] "
                            + invRS.getFlightCarrier() + " - " + Constants.FormatFltNum(Integer.parseInt(invRS.getFlightNumber())) + " - "
                            + invRS.getFlightDate() + " - "
                            + ((Constants.IsOnlineCarrier(invRS.getFlightCarrier())) ? "[ONLINE]" : "[OFFLINE]")
                    );
                    
                    if (invRS.getFlightLegs() != null)
                    {
                        System.out.println();
                        for (InventoryRS_FlightLeg Leg : invRS.getFlightLegs())
                        {
                            System.out.println("[LEG] "
                                + Leg.getOperatingCrrCode()+ " - " + Constants.FormatFltNum(Integer.parseInt(Leg.getOperatingFltNum())) + " - "
                                + Leg.getLegDate() + " - "
                                + "[" + Leg.getLegOrgn() + "] [" + Leg.getLegDstn() + "]" + " - "
                                + ((Constants.IsOnlineCarrier(Leg.getOperatingCrrCode())) ? "[ONLINE]" : "[OFFLINE]")
                            );

                        }
                    }
                    
                    if (invRS.getFlightSegments() != null)
                    {
                        System.out.println();
                        for (InventoryRS_FlightSegment Seg : invRS.getFlightSegments())
                        {
                            System.out.println("[SEG] "
                                + Constants.FormatFltNum(Integer.parseInt(Seg.getSegFltNum())) + " - "
                                + Seg.getSegDate() + " - "
                                + "[" + Seg.getSegOrgn() + "] [" + Seg.getSegDstn() + "]"
                            );

                        }
                    }
                }
            }
            
            /*
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
            }*/
        }
    }
}
