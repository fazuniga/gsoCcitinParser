/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS;

import com.latam.sci.ptic.gso.auxiliar.CcitinGSORegEx;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
/**
 *
 * @author 641515
 */
public class OTA_AirAvailRSParser {
    public OTA_AirAvailRSParser() { }
    
    public OTA_AirAvailRS OTA_AirAvailRSProcessLines(List<String> OTA_AirAvailRSLines)
    {
        OTA_AirAvailRS otaRS = null;
        OTA_AirAvailRS_OriginDestinationOption otaRS_Option = null;
        
        // System.out.println("##### OTA AIR AVAIL RS PARSING #####");
        Matcher m;
        
        if (OTA_AirAvailRSLines != null)
        {
            otaRS = new OTA_AirAvailRS();
            
            List<List<String>> Options = CcitinGSORegEx.IsolateSection(
                    OTA_AirAvailRSLines, 
                    CcitinGSORegEx.OTA_AirAvailRS_OriginDestinationOption_start, 
                    CcitinGSORegEx.OTA_AirAvailRS_OriginDestinationOption_end);
            
            if (Options != null)
            {
                List<OTA_AirAvailRS_OriginDestinationOption> options = null;
                
                for (List<String> Option : Options)
                {
                    for (String line : Option)
                    {
                        m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.OTA_AirAvailRS_OriginDestinationOption_start, line);
                        if (m.find())
                        {
                            if (options == null) { options = new ArrayList<>(); }
                            
                            String OriginDestinationRPH = m.group("OriginDestinationRPH");
                            
                            otaRS_Option = new OTA_AirAvailRS_OriginDestinationOption();
                            otaRS_Option.setOriginDestinationRPH(OriginDestinationRPH);
                        }
                    }
                    
                    List<List<String>> FlightSegments = CcitinGSORegEx.IsolateSection(
                            Option,
                            CcitinGSORegEx.OTA_AirAvailRS_FlightSegment_start,
                            CcitinGSORegEx.OTA_AirAvailRS_FlightSegment_end);
                    
                    List<OTA_AirAvailRS_FlightSegment> Segments = ProcessSegments(FlightSegments);
                    otaRS_Option.setOTA_AirAvailRSFlightSegments(Segments);
                    options.add(otaRS_Option);
                }
                otaRS.setOptions(options);
            }
        }
        
        return otaRS;
    }
    
    public List<OTA_AirAvailRS_FlightSegment> ProcessSegments(List<List<String>> FlightSegments)
    {
        Matcher m;
        List<OTA_AirAvailRS_FlightSegment> Segments = null;
        OTA_AirAvailRS_FlightSegment otaRS_FlightSegment = null;
        
        if (FlightSegments != null)
        {
            for (List<String> FlightSegment : FlightSegments)
            {
                for (String line : FlightSegment)
                {
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.OTA_AirAvailRS_FlightSegment_start, line);
                    if (m.find())
                    {
                        if (Segments == null) { Segments = new ArrayList<>(); }

                        String DepartureDateTime = m.group("DepartureDateTime");
                        String ArrivalDateTime = m.group("ArrivalDateTime");

                        String[] DepartureAux = DepartureDateTime.split("T");
                        String DepartureDate = DepartureAux[0];
                        String DepartureTime = DepartureAux[1];

                        String[] ArrivalAux = ArrivalDateTime.split("T");
                        String ArrivalDate = ArrivalAux[0];
                        String ArrivalTime = ArrivalAux[1];

                        String ConnectionInd = "";
                        try { ConnectionInd = m.group("ConnectionInd"); }
                        catch (Exception e) {}

                        String DOTInd = "";
                        try { DOTInd = m.group("DOTInd"); }
                        catch (Exception e) {}

                        String FlightNumber = m.group("FlightNumber");
                        String FlightRPH = m.group("FlightRPH");

                        otaRS_FlightSegment = new OTA_AirAvailRS_FlightSegment();

                        otaRS_FlightSegment.setDepartureDate(DepartureDate);
                        otaRS_FlightSegment.setArrivalDate(ArrivalDate);
                        otaRS_FlightSegment.setDepartureTime(DepartureTime);
                        otaRS_FlightSegment.setArrivalTime(ArrivalTime);
                        otaRS_FlightSegment.setConnectionInd(ConnectionInd);
                        otaRS_FlightSegment.setDOTInd(DOTInd);
                        otaRS_FlightSegment.setFlightNumber(FlightNumber);
                        otaRS_FlightSegment.setFlightRPH(FlightRPH);
                    }

                    // Origin
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.OTA_AirAvailRS_FlightSegment_Origin, line);
                    if (m.find())
                    {
                        String OriginLocation = m.group("OriginLocation");
                        otaRS_FlightSegment.setOriginLocation(OriginLocation);
                    }

                    // Destination
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.OTA_AirAvailRS_FlightSegment_Destination, line);
                    if (m.find())
                    {
                        String DestinationLocation = m.group("DestinationLocation");
                        otaRS_FlightSegment.setDestinationLocation(DestinationLocation);
                    }

                    // MarketingAirline
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.OTA_AirAvailRS_FlightSegment_MarketingAirline, line);
                    if (m.find())
                    {
                        String MarketingAirlineCode = m.group("MarketingAirlineCode");
                        String MarketingFlightNumber = m.group("MarketingFlightNumber");

                        otaRS_FlightSegment.setMarketingAirlineCode(MarketingAirlineCode);
                        otaRS_FlightSegment.setMarketingFlightNumber(MarketingFlightNumber);
                    }
                }
                Segments.add(otaRS_FlightSegment);
            }
        }
        
        return Segments;
    }
}
