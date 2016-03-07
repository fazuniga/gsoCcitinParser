/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRQ;

import com.latam.sci.ptic.gso.auxiliar.CcitinGSORegEx;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 *
 * @author fazuniga
 */
public class RTDPAvailabilityRQParser {
    
    public RTDPAvailabilityRQ RTDPAvailabilityRQProcessLines(List<String> RTDPAvailabilityRQLines)
    {
        RTDPAvailabilityRQ rtdpRQ = null;
        
        // List<RTDPAvailabilityRQElement> rtdpRQElements = new ArrayList<>();
        // RTDPAvailabilityRQElement rtdpRQe = new RTDPAvailabilityRQElement();
        
        List<RTDPAvailabilityRQ_Segment> rtdpRQSegments = null;
        
        System.out.println("##### RTDPAvailabilityRQ PARSING #####");
        Matcher m;
        if (RTDPAvailabilityRQLines != null)
        {
            rtdpRQ = new RTDPAvailabilityRQ();
            
            for (String line : RTDPAvailabilityRQLines)
            {
                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRQ_UserInfo, line);
                if (m.find())
                {
                    String AirlineGDS = m.group("AirlineGDS");
                    String POSCity = m.group("POSCity");
                    String POSCountry = m.group("POSCountry");

                    rtdpRQ.setAirlineGDS(AirlineGDS);
                    rtdpRQ.setPOSCity(POSCity);
                    rtdpRQ.setPOSCountry(POSCountry);
                }

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRQ_Segment, line);
                if (m.find()) 
                {
                    if (rtdpRQSegments == null) { rtdpRQSegments = new ArrayList<>(); }

                    RTDPAvailabilityRQ_Segment rtdpRQSegment = new RTDPAvailabilityRQ_Segment();

                    String Action = m.group("Action");
                    String AirlineCode = m.group("AirlineCode");
                    String DepartureDate = m.group("DepartureDate");
                    String Origin = m.group("Origin");
                    String Destination = m.group("Destination");
                    int FlightNumber = Integer.parseInt(m.group("FlightNumber"));

                    rtdpRQSegment.setAction(Action);
                    rtdpRQSegment.setAirlineCode(AirlineCode);
                    rtdpRQSegment.setDepartureDate(DepartureDate);
                    rtdpRQSegment.setOrigin(Origin);
                    rtdpRQSegment.setDestination(Destination);
                    rtdpRQSegment.setFlightNumber(FlightNumber);

                    rtdpRQSegments.add(rtdpRQSegment);
                }

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRQ_OnDInfo, line);
                if (m.find())
                {
                    String Origin = m.group("Origin");
                    String Destination = m.group("Destination");

                    rtdpRQ.setOrigin(Origin);
                    rtdpRQ.setDestination(Destination);
                }

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRQ_RPIInfo, line);
                if (m.find())
                {
                    int NumberOfSeats = Integer.parseInt(m.group("NumberOfSeats"));

                    rtdpRQ.setNumberOfSeats(NumberOfSeats);
                }
            }
            // Finish the element
            rtdpRQ.setSegments(rtdpRQSegments);
        }
        
        return rtdpRQ;
    }
}
