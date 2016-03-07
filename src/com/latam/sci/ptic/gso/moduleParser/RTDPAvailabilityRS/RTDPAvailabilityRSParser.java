/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRS;

import com.latam.sci.ptic.gso.auxiliar.CcitinGSORegEx;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 *
 * @author 641515
 */
public class RTDPAvailabilityRSParser {
    
    public RTDPAvailabilityRS RTDPAvailabilityRSProcessLines(List<String> RTDPAvailabilityRSLines)
    {
        RTDPAvailabilityRS rtdpRS = null;
        List<RTDPAvailabilityRS_Segment> rtdpRSSegments = null;
        
        System.out.println("##### RTDPAvailabilityRS PARSING #####");
        Matcher m;
        
        if (RTDPAvailabilityRSLines != null)
        {
            rtdpRS = new RTDPAvailabilityRS();
            
            for (String line : RTDPAvailabilityRSLines)
            {
                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRS_UserInfo, line);
                if (m.find())
                {
                    String AirlineGDS = m.group("AirlineGDS");
                    String POSCity = m.group("POSCity");
                    String POSCountry = m.group("POSCountry");

                    rtdpRS.setAirlineGDS(AirlineGDS);
                    rtdpRS.setPOSCity(POSCity);
                    rtdpRS.setPOSCountry(POSCountry);
                }

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment, line);
                if (m.find()) 
                {
                    if (rtdpRSSegments == null) { rtdpRSSegments = new ArrayList<>(); }

                    RTDPAvailabilityRS_Segment rtdpRSSegment = new RTDPAvailabilityRS_Segment();

                    String Action = m.group("Action");
                    String AirlineCode = m.group("AirlineCode");
                    String FlightNumber = m.group("FlightNumber");
                    String ArrivalDateAdj = m.group("ArrivalDateAdj");
                    String ArrivalTime = m.group("ArrivalTime");
                    String DepartureDate = m.group("DepartureDate");
                    String DepartureTime = m.group("DepartureTime");
                    String Origin = m.group("Origin");
                    String Destination = m.group("Destination");
                    String FareClasses = m.group("FareClasses");
                    int NoOfLegs = Integer.parseInt(m.group("NoOfLegs"));
                    String RTDPActive = m.group("RTDPActive");
                    
                    rtdpRSSegment.setAction(Action);
                    rtdpRSSegment.setAirlineCode(AirlineCode);
                    rtdpRSSegment.setFlightNumber(FlightNumber);
                    rtdpRSSegment.setArrivalDateAdj(ArrivalDateAdj);
                    rtdpRSSegment.setArrivalTime(ArrivalTime);
                    rtdpRSSegment.setDepartureDate(DepartureDate);
                    rtdpRSSegment.setDepartureTime(DepartureTime);
                    rtdpRSSegment.setOrigin(Origin);
                    rtdpRSSegment.setDestination(Destination);
                    rtdpRSSegment.setFareClasses(FareClasses);
                    rtdpRSSegment.setNoOfLegs(NoOfLegs);
                    rtdpRSSegment.setRTDPActive(RTDPActive);
                    
                    rtdpRSSegments.add(rtdpRSSegment);
                }

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRS_OnDInfo, line);
                if (m.find())
                {
                    String Origin = m.group("Origin");
                    String Destination = m.group("Destination");

                    rtdpRS.setOrigin(Origin);
                    rtdpRS.setDestination(Destination);
                }

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRS_RPIInfo, line);
                if (m.find())
                {
                    int NumberOfSeats = Integer.parseInt(m.group("NumberOfSeats"));

                    rtdpRS.setNumberOfSeats(NumberOfSeats);
                }
            }
            // Finish the element
            rtdpRS.setSegments(rtdpRSSegments);
        }
        
        return rtdpRS;
    }
}
