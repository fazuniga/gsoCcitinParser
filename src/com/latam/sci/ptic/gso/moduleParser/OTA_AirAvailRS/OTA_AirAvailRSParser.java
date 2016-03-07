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
import static com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRSElement.OTA_AirAvailRS_OriginDestinationOption_start;
import static com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRSElement.OTA_AirAvailRS_OriginDestinationOption_end;
import static com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRSFlightSegment.OTA_AirAvailRSFlightSegment_Destination;
import static com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRSFlightSegment.OTA_AirAvailRSFlightSegment_MarketingAirline;
import static com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRSFlightSegment.OTA_AirAvailRSFlightSegment_Origin;
import static com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRSFlightSegment.OTA_AirAvailRSFlightSegment_end;
import static com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRSFlightSegment.OTA_AirAvailRSFlightSegment_start;

/**
 *
 * @author 641515
 */
public class OTA_AirAvailRSParser {
    public OTA_AirAvailRSParser() { }
    
    public OTA_AirAvailRS OTA_AirAvailRSProcessLines(List<String> OTA_AirAvailRSLines)
    {
        OTA_AirAvailRS otaRS = new OTA_AirAvailRS();
        List<OTA_AirAvailRSElement> otaRSElements = new ArrayList<>();
        
        OTA_AirAvailRSElement otaRSe = null;
        List<OTA_AirAvailRSFlightSegment> otaRSe_FlightSegments  = null;
        OTA_AirAvailRSFlightSegment otaRSe_FlightSegment = null;
        
        System.out.println("##### OTA AIR AVAIL RS PARSING #####");
        Matcher m;
        
        for (String line : OTA_AirAvailRSLines)
        {
            m = CcitinGSORegEx.RegExTest(OTA_AirAvailRS_OriginDestinationOption_start, line);
            if (m.find())
            {
                otaRSe = new OTA_AirAvailRSElement();
                otaRSe_FlightSegments = new ArrayList<>();
                
                String OriginDestinationRPH = m.group("OriginDestinationRPH");
                otaRSe.setOriginDestinationRPH(OriginDestinationRPH);
            }
            
            m = CcitinGSORegEx.RegExTest(OTA_AirAvailRS_OriginDestinationOption_end, line);
            if (m.find())
            {
                otaRSe.setOTA_AirAvailRSFlightSegments(otaRSe_FlightSegments);
                otaRSElements.add(otaRSe); 
                otaRSe_FlightSegments = null;
            }
            
            m = CcitinGSORegEx.RegExTest(OTA_AirAvailRSFlightSegment_start, line);
            if (m.find())
            {
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
                
                otaRSe_FlightSegment = new OTA_AirAvailRSFlightSegment();
                
                otaRSe_FlightSegment.setDepartureDate(DepartureDate);
                otaRSe_FlightSegment.setArrivalDate(ArrivalDate);
                otaRSe_FlightSegment.setDepartureTime(DepartureTime);
                otaRSe_FlightSegment.setArrivalTime(ArrivalTime);
                otaRSe_FlightSegment.setConnectionInd(ConnectionInd);
                otaRSe_FlightSegment.setDOTInd(DOTInd);
                otaRSe_FlightSegment.setFlightNumber(FlightNumber);
                otaRSe_FlightSegment.setFlightRPH(FlightRPH);
            }
            
            // If the closure of a flight segment is found, it is added to the list of FlightSegments
            m = CcitinGSORegEx.RegExTest(OTA_AirAvailRSFlightSegment_end, line);
            if (m.find())
            {
                otaRSe_FlightSegments.add(otaRSe_FlightSegment);
                // otaRSe_FlightSegment = null;
            }
            
            // Origin
            m = CcitinGSORegEx.RegExTest(OTA_AirAvailRSFlightSegment_Origin, line);
            if (m.find())
            {
                String OriginLocation = m.group("OriginLocation");
                otaRSe_FlightSegment.setOriginLocation(OriginLocation);
            }
            
            // Destination
            m = CcitinGSORegEx.RegExTest(OTA_AirAvailRSFlightSegment_Destination, line);
            if (m.find())
            {
                String DestinationLocation = m.group("DestinationLocation");
                otaRSe_FlightSegment.setDestinationLocation(DestinationLocation);
            }
            
            // MarketingAirline
            m = CcitinGSORegEx.RegExTest(OTA_AirAvailRSFlightSegment_MarketingAirline, line);
            if (m.find())
            {
                String MarketingAirlineCode = m.group("MarketingAirlineCode");
                String MarketingFlightNumber = m.group("MarketingFlightNumber");
                
                otaRSe_FlightSegment.setMarketingAirlineCode(MarketingAirlineCode);
                otaRSe_FlightSegment.setMarketingFlightNumber(MarketingFlightNumber);
            }
        }
        
        otaRS.setOta_AirAvailRS(otaRSElements);       
        return otaRS;
    }
}
