/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.auxiliar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fazuniga
 */
public final class CcitinGSORegEx {
    // CCITIN Request
    public static String CcitinRQ_start = "^.*<[A-Za-z0-9:]*ccitinRequest.*$";
    public static String CcitinRQ_end = "^.*</[A-Za-z0-9:]*ccitinRequest.*$";
    public static String CcitinRQ_dir_start = "^\\s*<Direction id=\"(?<DirectionID>\\d+)\" origin=\"(?<Origin>[A-Z]{3})\" destination=\"(?<Destination>[A-Z]{3})\" cityPOS=\"(?<cityPOS>[A-Z]{3})\" countryPOS=\"(?<countryPOS>[A-Z]{2})\" directFlightsOnly=\"(?<directFlights>[no|yes]+)\" allowOAL=\"(?<allowOAL>[no|yes]+)\">$";
    public static String CcitinRQ_dir_end = "^\\s*</Direction>.*$";
    public static String CcitinRQ_dir_data = "^\\s*<DepartureDate startDate=\"(?<startDate>\\d{8})\" endDate=\"(?<endDate>\\d{8})\" frequency=\"(?<frequency>\\d{7})\" startTime=\"(?<startTime>\\d{4})\" endTime=\"(?<endTime>\\d{4})\" orientation=\"(?<orientation>[A-Z]{1})\".*$";
    
    // CCITIN Response
    public static String CcitinRS_start = "^.*<Response>.*$";
    public static String CcitinRS_end = "^.*</Response>.*$";
    public static String CcitinRS_Dir = "^.*<Direction id=\"(\\d+)\">.*$";
    
    // OTA_AirAvail
    public static String OTA_AirAvailRQ_start = "^.*<[A-Za-z0-9:]*OTA_AirAvailRQ.*$";
    public static String OTA_AirAvailRQ_end = "^.*</[A-Za-z0-9:]*OTA_AirAvailRQ.*$";
    public static String OTA_AirAvailRQ_Flight = "^\\s*<ns:FlightQualifiers Charters=\"(?<Charters>[true|false]*)\" DirectOnly=\"(?<DirectOnly>[true|false]*)\" ExcludeCodeshares=\"(?<ExcludeCodeshares>[true|false]*)\".*$";
    public static String OTA_AirAvailRQ_times = "^\\s*<ns:DepartureTime WindowBefore=\"(?<MaxDeparture>[0-9:]{5})\" WindowAfter=\"(?<MinDeparture>[0-9:]{5})\".*$";
    public static String OTA_AirAvailRQ_Segment = "^\\s*<ns:FlightSegment DepartureDateTime=\"(?<DepDate>[0-9-]{10})\">.*$";
    public static String OTA_AirAvailRQ_Origin = "^\\s*<ns:OriginLocation LocationCode=\"(?<Origin>[A-Z]{3})\"/>.*$";
    public static String OTA_AirAvailRQ_Destination = "^\\s*<ns:DestinationLocation LocationCode=\"(?<Destination>[A-Z]{3})\"/>.*$";    
    public static String OTA_AirAvailRQ_MoreAvailability = "^\\s*<ns:AdditionalAvailability Ind=\"(?<MoreAvailability>[true|false]*)\".*$";
    
    public static String OTA_AirAvailRS_start = "^.*<[A-Za-z0-9:]*OTA_AirAvailRS.*$";
    public static String OTA_AirAvailRS_end = "^.*</[A-Za-z0-9:]*OTA_AirAvailRS.*$";
    
    // Inventory - REQUEST
    public static String InventoryRQ_start = "^*<[A-Za-z0-9:]*InventoryControlFlightDisplayRQ.*$";
    public static String InventoryRQ_end = "^*</[A-Za-z0-9:]*InventoryControlFlightDisplayRQ.*$";
    
    public static String InventoryRQ_Flight_start = "^\\s*<[A-Za-z0-9:]*Flight>.*$";
    public static String InventoryRQ_Flight_end = "^\\s*</[A-Za-z0-9:]*Flight>.*$";
    public static String InventoryRQ_FlightCarrier = "^\\s*<[A-Za-z0-9:]*Carrier>(?<FlightCarrier>[A-Z0-9]{2}).*$";
    public static String InventoryRQ_FlightNumber = "^\\s*<[A-Za-z0-9:]*Number>(?<FlightNumber>[0-9]+).*$";
    public static String InventoryRQ_FlightDate = "^\\s*<[A-Za-z0-9:]*Date>(?<FlightDate>[0-9-]{10}).*$";
    
    // Inventory - RESPONSE
    public static String InventoryRS_start = "^*<[A-Za-z0-9:]*InventoryControlFlightDisplayRS.*$";
    public static String InventoryRS_end = "^*</[A-Za-z0-9:]*InventoryControlFlightDisplayRS.*$";
    
    public static String InventoryRS_Flight_start = InventoryRQ_Flight_start;
    public static String InventoryRS_Flight_end = InventoryRQ_Flight_end;
    
    public static String InventoryRS_FlightCarrier = InventoryRQ_FlightCarrier;
    public static String InventoryRS_FlightNumber = InventoryRQ_FlightNumber;
    public static String InventoryRS_FlightDate = InventoryRQ_FlightDate;
    
    // Inventory - Flight Segment
    public static String InventoryRS_FlightSegment_start = "^\\s*<[A-Za-z0-9:]*Segment>.*$";
    public static String InventoryRS_FlightSegment_end = "^\\s*</[A-Za-z0-9:]*Segment>.*$";
    public static String InventoryRS_FlightSegment_Origin = "^\\s*<[A-Za-z0-9:]*Origin>(?<Origin>[A-Z]{3}).*$";
    public static String InventoryRS_FlightSegment_Destination = "^\\s*<[A-Za-z0-9:]*Destination>(?<Destination>[A-Z]{3}).*$";
    public static String InventoryRS_FlightSegment_Date = InventoryRS_FlightDate;
    public static String InventoryRS_FlightSegment_Number = "^\\s*<[A-Za-z0-9:]*FltNum>(?<SegFltNum>[0-9]+).*$";
    public static String InventoryRS_FlightSegment_NumLegs = "^\\s*<[A-Za-z0-9:]*NumLegs>(?<SegNumLegs>[0-9]+).*$";
    public static String InventoryRS_FlightSegment_DepTime = "^\\s*<[A-Za-z0-9:]*DepartureTime>(?<DepTime>[0-9]+).*$";
    public static String InventoryRS_FlightSegment_DepDateAdj = "^\\s*<[A-Za-z0-9:]*DepartureDateAdj>(?<DptDateAdj>[0-9]+).*$";
    public static String InventoryRS_FlightSegment_ArrTime = "^\\s*<[A-Za-z0-9:]*ArrivalTime>(?<ArrTime>[0-9]+).*$";
    public static String InventoryRS_FlightSegment_ArrDateAdj = "^\\s*<[A-Za-z0-9:]*ArrivalDateAdjustment>(?<ArrDateAdj>[0-9]+).*$";
    public static String InventoryRS_FlightSegment_FareClasses = "^\\s*<[A-Za-z0-9:]*FareClassAvlSeq>(?<FareClasses>[0-9]+).*$";
    
    public static String InventoryRS_FlightSegment_SeatsSold_start = "^\\s*<[A-Za-z0-9:]*SeatsSold>.*$";
    public static String InventoryRS_FlightSegment_SeatsSold_end = "^\\s*</[A-Za-z0-9:]*SeatsSold>.*$";
    public static String InventoryRS_FlightSegment_MaxSeats_start = "^\\s*<[A-Za-z0-9:]*MaxSeats>.*$";
    public static String InventoryRS_FlightSegment_MaxSeats_end = "^\\s*</[A-Za-z0-9:]*MaxSeats>.*$";
    public static String InventoryRS_FlightSegment_GroupCounts_start = "^\\s*<[A-Za-z0-9:]*CorporateGroupCounts>.*$";
    public static String InventoryRS_FlightSegment_GroupCounts_end = "^\\s*</[A-Za-z0-9:]*CorporateGroupCounts>.*$";
    
    public static String InventoryRS_FlightSegment_SeatsClass = "\\s*<Seats class=\"(?<ClsCode>[A-Z]{1})\">(?<Amount>\\d+)</Seats>.*$";
    
    // Inventory - Flight Leg
    public static String InventoryRS_FlightLeg_start = "^\\s*<[A-Za-z0-9:]*Leg>.*$";
    public static String InventoryRS_FlightLeg_end = "^\\s*</[A-Za-z0-9:]*Leg>.*$";
    
    public static String InventoryRS_FlightLeg_Origin = InventoryRS_FlightSegment_Origin;
    public static String InventoryRS_FlightLeg_Destination = InventoryRS_FlightSegment_Destination;
    public static String InventoryRS_FlightLeg_Date = InventoryRS_FlightDate;
    public static String InventoryRS_FlightLeg_DepTime = InventoryRS_FlightSegment_DepTime;
    public static String InventoryRS_FlightLeg_ArrTime = InventoryRS_FlightSegment_ArrTime;
    public static String InventoryRS_FlightLeg_DepDateAdj = InventoryRS_FlightSegment_DepDateAdj;
    public static String InventoryRS_FlightLeg_ArrDateAdj = InventoryRS_FlightSegment_ArrDateAdj;
    
    public static String InventoryRS_FlightLeg_SeatsSold_start = InventoryRS_FlightSegment_SeatsSold_start;
    public static String InventoryRS_FlightLeg_SeatsSold_end = InventoryRS_FlightSegment_SeatsSold_end;
    public static String InventoryRS_FlightLeg_LegAuth_start = "^\\s*<[A-Za-z0-9:]*LegAuth>.*$";
    public static String InventoryRS_FlightLeg_LegAuth_end = "^\\s*</[A-Za-z0-9:]*LegAuth>.*$";
    public static String InventoryRS_FlightLeg_Capacities_start = "^\\s*<[A-Za-z0-9:]*Capacities>.*$";
    public static String InventoryRS_FlightLeg_Capacities_end = "^\\s*</[A-Za-z0-9:]*Capacities>.*$";
    public static String InventoryRS_FlightLeg_CabinNesting_start = "^\\s*<[A-Za-z0-9:]*NestingOrder>.*$";
    public static String InventoryRS_FlightLeg_CabinNesting_end = "^\\s*</[A-Za-z0-9:]*NestingOrder>.*$";
       
    public static String InventoryRS_FlightLeg_CabinCapacity = "\\s*<Cabin index=\"(?<CabinIndex>\\d+)\">(?<Capacity>\\d+)</Cabin>.*$";
    public static String InventoryRS_FlightLeg_CabinNesting = "\\s*<Cabin index=\"(?<CabinIndex>\\d+)\">(?<NestingOrder>[A-Z]+)</Cabin>.*$";
    
    // RTDP Webservice
    public static String AirInventoryRTDPDisplayRQ_start = "^.*<[A-Za-z0-9:]*AirInventoryRTDPDisplayRQ.*$";
    public static String AirInventoryRTDPDisplayRQ_end = "^.*</[A-Za-z0-9:]*AirInventoryRTDPDisplayRQ.*$";
    public static String AirInventoryRTDPDisplayRQ_UserInfo = "^\\s*<[A-Za-z0-9:]*UserInfo AirlineGDS=\"(?<AirlineGDS>[A-Z0-9]+)\" ID=\"[A-Z0-9]+\" POSCity=\"(?<POSCity>[A-Z]{3})\" POSCountry=\"(?<POSCountry>[A-Z]{2})\".*$";
    public static String AirInventoryRTDPDisplayRQ_Segment = "^\\s*<[A-Za-z0-9:]*Segment Action=\"(?<Action>[true|false]+)\" AirlineCode=\"(?<AirlineCode>[A-Z0-9]{2})\" DepartureDate=\"(?<DepartureDate>[0-9-]{10})\" Destination=\"(?<Destination>[A-Z]{3})\" FlightNumber=\"(?<FlightNumber>[0-9]+)\" Origin=\"(?<Origin>[A-Z]{3})\".*$";
    public static String AirInventoryRTDPDisplayRQ_OnDInfo = "^\\s*<[A-Za-z0-9:]*OnDInfo Destination=\"(?<Destination>[A-Z]{3})\" Origin=\"(?<Origin>[A-Z]{3})\".*$";
    public static String AirInventoryRTDPDisplayRQ_RPIInfo = "^\\s*<[A-Za-z0-9:]*RPIInfo NoOfSeats=\"(?<NumberOfSeats>[0-9]+)\".*$";
    
    public static String AirInventoryRTDPDisplayRS_start = "^.*<[A-Za-z0-9:]*AirInventoryRTDPDisplayRS.*$";
    public static String AirInventoryRTDPDisplayRS_end = "^.*</[A-Za-z0-9:]*AirInventoryRTDPDisplayRS.*$";
    public static String AirInventoryRTDPDisplayRS_UserInfo = AirInventoryRTDPDisplayRQ_UserInfo;
    public static String AirInventoryRTDPDisplayRS_Segment = AirInventoryRTDPDisplayRQ_Segment;
    public static String AirInventoryRTDPDisplayRS_OnDInfo = AirInventoryRTDPDisplayRQ_OnDInfo;
    public static String AirInventoryRTDPDisplayRS_RPIInfo = AirInventoryRTDPDisplayRQ_RPIInfo;
    
    
    public static Matcher RegExTest(String regexStr, String line)
    {
        Pattern r = Pattern.compile(regexStr);
        return r.matcher(line);
    }
    
    public static List<List<String>> IsolateSection(List<String> lines, String startRegExStr, String endRegExStr) {
        return IsolateSection(lines, startRegExStr, endRegExStr, true);
    }
        
    public static List<List<String>> IsolateSection(List<String> lines, String startRegExStr, String endRegExStr, Boolean trim) {
        List<List<String>> finalLines = null;
        List<String> sectionLines = null;
        Matcher m;
        
        for (String line : lines)
        {
            if (finalLines == null) { finalLines = new ArrayList<>(); }
            
            m = CcitinGSORegEx.RegExTest(startRegExStr, line);
            if (m.find()) { sectionLines = new ArrayList<>(); }
            
            m = CcitinGSORegEx.RegExTest(endRegExStr, line);
            if (m.find())
            {
                if (trim) {
                    sectionLines.add(line.trim());
                }
                else {
                    sectionLines.add(line);
                }
                
                finalLines.add(sectionLines);
                sectionLines = null;
            }
            
            if (sectionLines != null) { sectionLines.add(line); }
        }
        
        if (finalLines != null) {
            if (finalLines.isEmpty()) { finalLines = null; }
        }
        
        return finalLines;
    }
}
