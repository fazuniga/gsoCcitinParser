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
    public static String CcitinRS_Direction_start = "^.*<Direction id=\"(?<DirectionID>\\d+)\">.*$";
    public static String CcitinRS_Direction_end = "^.*</Direction>.*$";
    public static String CcitinRS_Direction_Option_start = "^\\s*<Option EFT=\"(?<EFT>\\d+)\" id=\"(?<OptionID>\\d+)\">.*$";
    public static String CcitinRS_Direction_Option_end = "^\\s*</Option>.*$";
    public static String CcitinRS_Direction_OptionFlight_start = "^\\s*<Flight EFT=\"(?<EFT>\\d+)\" aircraftType=\"(?<aircraftType>\\d+)\" arrivalDate=\"(?<arrivalDate>\\d{8})\" arrivalTime=\"(?<arrivalTime>\\d+)\" carrier=\"(?<carrier>[A-Z0-9]{2})\" departureDate=\"(?<departureDate>\\d{8})\" departureTime=\"(?<departureTime>\\d+)\" destination=\"(?<destination>[A-Z]{3})\" flightNumber=\"(?<flightNumber>\\d+)\" origin=\"(?<origin>[A-Z]{3})\" sequenceNumber=\"(?<sequenceNumber>\\d+)\".*$";
    public static String CcitinRS_Direction_OptionFlight_end = "^\\s*</Flight>.*$";
    public static String CcitinRS_Direction_OptionFlight_Leg_start = "^\\s*<Leg allowBooking=\"(?<allowBooking>\\d+)\" allowGroup=\"(?<allowGroup>\\d+)\" allowSale=\"(?<allowSale>\\d+)\" allowWaitList=\"(?<allowWaitList>\\d+)\" dateOffset=\"(?<dateOffset>\\d+)\" destination=\"(?<LegDstn>[A-Z]{3})\" sequenceNumber=\"(?<sequenceNumber>\\d+)\".*$";
    public static String CcitinRS_Direction_OptionFlight_Leg_end = "^\\s*</Leg>.*$";
    public static String CcitinRS_Direction_OptionFlight_Leg_Cmp_start = "^\\s*<Compartment adjustedCapacity=\"(?<AdjustedCapacity>\\d+)\" capacity=\"(?<Capacity>\\d+)\" code=\"(?<CmpCode>[A-Z]{1})\" groupBooked=\"(?<groupBooked>\\d+)\" odFactor=\"(?<odFactor>\\d+)\" totalAU=\"(?<totalAU>\\d+)\" totalBooked=\"(?<totalBooked>\\d+)\".*$";
    public static String CcitinRS_Direction_OptionFlight_Leg_Cmp_end = "\\s*</Compartment>.*$";
    public static String CcitinRS_Direction_OptionFlight_Leg_Cmp_Class = "^\\s*<Class allowBooking=\"(?<allowBooking>\\d+)\" allowGroup=\"(?<allowGroup>\\d+)\" allowSale=\"(?<allowSale>\\d+)\" allowWaitList=\"(?<allowWaitList>\\d+)\" code=\"(?<ClsCode>[A-Z]{1})\" groupBooked=\"(?<groupBooked>\\d+)\" totalBooked=\"(?<totalBooked>\\d+)\".*$";
    public static String CcitinRS_Direction_OptionFlight_Seg_start = "^\\s*<Segment allowBooking=\"(?<allowBooking>\\d+)\" allowGroup=\"(?<allowGroup>\\d+)\" allowSale=\"(?<allowSale>\\d+)\" allowWaitList=\"(?<allowWaitList>\\d+)\" destination=\"(?<SegDstn>[A-Z]{3})\" origin=\"(?<SegOrgn>[A-Z]{3})\" sequenceNumber=\"(?<sequenceNumber>\\d+)\".*$";
    public static String CcitinRS_Direction_OptionFlight_Seg_end = "^\\s*</Segment>.*$";
    public static String CcitinRS_Direction_OptionFlight_Seg_Cmp_start = "^\\s*<Compartment code=\"(?<CmpCode>[A-Z]{1})\" groupBooked=\"(?<groupBooked>\\d+)\".*$";
    public static String CcitinRS_Direction_OptionFlight_Seg_Cmp_end = "\\s*</Compartment>.*$";
    public static String CcitinRS_Direction_OptionFlight_Seg_Cmp_Class = "^\\s*<Class allowBooking=\"(?<allowBooking>\\d+)\" allowGroup=\"(?<allowGroup>\\d+)\" allowSale=\"(?<allowSale>\\d+)\" allowWaitList=\"(?<allowWaitList>\\d+)\" code=\"(?<ClsCode>[A-Z]{1})\" groupBooked=\"(?<groupBooked>\\d+)\" limit=\"(?<limit>\\d+)\" odAvailability=\"(?<odAvailability>\\d+)\" totalBooked=\"(?<totalBooked>\\d+)\".*$";
    
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
    public static String OTA_AirAvailRS_OriginDestinationOption_start = "^\\s*<OriginDestinationOption RPH=\"(?<OriginDestinationRPH>\\d+)\".*$";
    public static String OTA_AirAvailRS_OriginDestinationOption_end = "^\\s*</OriginDestinationOption>.*$";
    public static String OTA_AirAvailRS_FlightSegment_start = "^\\s*<FlightSegment ArrivalDateTime=\"(?<ArrivalDateTime>[0-9:T-]{11})\"[ ]*(ConnectionInd=\"(?<ConnectionInd>[Y|N]*?)\")?[ ]*(DOT_Ind=\"(?<DOTInd>[Y|N]*?)\")? DepartureDateTime=\"(?<DepartureDateTime>[0-9:T-]{11})\" FlightNumber=\"(?<FlightNumber>\\d+)\" RPH=\"(?<FlightRPH>\\d+)\".*$";
    public static String OTA_AirAvailRS_FlightSegment_end = "^\\s*</FlightSegment>";
    
    public static String OTA_AirAvailRS_FlightSegment_Origin = "^\\s*<OriginLocation LocationCode=\"(?<OriginLocation>[A-Z]{3})\".*$";
    public static String OTA_AirAvailRS_FlightSegment_Destination = "^\\s*<DestinationLocation LocationCode=\"(?<DestinationLocation>[A-Z]{3})\".*$";
    public static String OTA_AirAvailRS_FlightSegment_MarketingAirline = "^\\s*<MarketingAirline Code=\"(?<MarketingAirlineCode>[A-Z0-9]{2})\" FlightNumber=\"(?<MarketingFlightNumber>\\d+).*$";

    
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
    public static String AirInventoryRTDPDisplayRS_RPIInfo = AirInventoryRTDPDisplayRQ_RPIInfo;
    
    public static String AirInventoryRTDPDisplayRS_Segment_start = "^\\s*<[A-Za-z0-9:]*Segment Action=\"(?<Action>[true|false]+)\" AirlineCode=\"(?<AirlineCode>[A-Z0-9]{2})\" (ArrivalDateAdj=\"(?<ArrivalDateAdj>\\d+)\")?.*ArrivalTime=\"(?<ArrivalTime>\\d+)\" DepartureDate=\"(?<DepartureDate>[0-9-]{10})\" DepartureTime=\"(?<DepartureTime>\\d+)\" Destination=\"(?<Destination>[A-Z]{3})\" FareclassAvlSeq=\"(?<FareClasses>[A-Z]+)\" FlightNumber=\"(?<FlightNumber>[0-9]+)\" NoOfLegs=\"(?<NoOfLegs>\\d+)\" Origin=\"(?<Origin>[A-Z]{3})\" RTDPActive=\"(?<RTDPActive>[true|false]+)\".*$";
    public static String AirInventoryRTDPDisplayRS_Segment_end = "^\\s*</[A-Za-z0-9:]*Segment>$";
    public static String AirInventoryRTDPDisplayRS_Segment_SeatsAvail_start = "^\\s*<[A-Za-z0-9:]*SeatsAvail.*$";
    public static String AirInventoryRTDPDisplayRS_Segment_SeatsAvail_end = "^\\s*</[A-Za-z0-9:]*SeatsAvail.*$";
    public static String AirInventoryRTDPDisplayRS_Segment_RTDPAvail_start = "^\\s*<[A-Za-z0-9:]*RTDPAvail.*$";
    public static String AirInventoryRTDPDisplayRS_Segment_RTDPAvail_end = "^\\s*</[A-Za-z0-9:]*RTDPAvail.*$";
    public static String AirInventoryRTDPDisplayRS_Segment_SeatsSold_start = InventoryRS_FlightSegment_SeatsSold_start;
    public static String AirInventoryRTDPDisplayRS_Segment_SeatsSold_end = InventoryRS_FlightSegment_SeatsSold_end;
    
    public static String AirInventoryRTDPDisplayRS_Segment_Leg_start = "\\s*<[A-Za-z0-9:]*Leg AirlineCode=\"(?<AirlineCode>[A-Z0-9]{2})\" (ArrivalDateAdj=\"(?<ArrivalDateAdj>\\d+)\")?.*ArrivalTime=\"(?<ArrivalTime>\\d+)\" Cabins=\"(?<LegCabins>[FJY]+)\" DepartureDate=\"(?<DepartureDate>[0-9-]{10})\" DepartureTime=\"(?<DepartureTime>\\d+)\" Destination=\"(?<Destination>[A-Z]{3})\" FareCabins=\"(?<FareCabins>[A-Z]+)\" FlightNumber=\"(?<FlightNumber>[0-9]+)\" Origin=\"(?<Origin>[A-Z]{3})\" RTDPActive=\"(?<RTDPActive>[true|false]+)\".*$";
    public static String AirInventoryRTDPDisplayRS_Segment_Leg_end = "^\\s*</[A-Za-z0-9:]*Leg.*$";
    public static String AirInventoryRTDPDisplayRS_Leg_CabinCapacities_start = "^\\s*<[A-Za-z0-9:]*CabinCapacities.*$";
    public static String AirInventoryRTDPDisplayRS_Leg_CabinCapacities_end = "^\\s*</[A-Za-z0-9:]*CabinCapacities.*$";
    public static String AirInventoryRTDPDisplayRS_Leg_CabinAuth_start = "^\\s*<[A-Za-z0-9:]*CabinAuth.*$";
    public static String AirInventoryRTDPDisplayRS_Leg_CabinAuth_end = "^\\s*</[A-Za-z0-9:]*CabinAuth.*$";
    public static String AirInventoryRTDPDisplayRS_Leg_CabinSeatsSold_start = "^\\s*<[A-Za-z0-9:]*CabinSeatsSold.*$";
    public static String AirInventoryRTDPDisplayRS_Leg_CabinSeatsSold_end = "^\\s*</[A-Za-z0-9:]*CabinSeatsSold.*$";
    public static String AirInventoryRTDPDisplayRS_Leg_CabinSeatsAvail_start = "^\\s*<[A-Za-z0-9:]*CabinSeatsAvail.*$";
    public static String AirInventoryRTDPDisplayRS_Leg_CabinSeatsAvail_end = "^\\s*</[A-Za-z0-9:]*CabinSeatsAvail.*$";
    
    public static String AirInventoryRTDPDisplayRS_Segment_ClassAvailability = "^\\s*<Seats Class=\"(?<ClsCode>[A-Z]{1})\".*>(?<Availability>\\d+)<\\/Seats>.*$";
    public static String AirInventoryRTDPDisplayRS_Segment_CabinData = "^\\s*<Cabin Index=\"(?<CabinIndex>\\d+)\".*>(?<Amount>\\d+)<\\/Cabin>.*$";
    
    public static String AirInventoryRTDPDisplayRS_OnDInfo_start = AirInventoryRTDPDisplayRQ_OnDInfo;
    public static String AirInventoryRTDPDisplayRS_OnDInfo_end = "^\\s*</[A-Za-z0-9:]*OnDInfo.*$";
    public static String AirInventoryRTDPDisplayRS_OnDInfo_SegIndex = "^\\s*<SegIndex>(?<SegIndex>\\d+)<\\/SegIndex>.*$";
    public static String AirInventoryRTDPDisplayRS_OnDInfo_Fares = "^\\s*<Fares AdjustedFareValue=\"(?<AdjustedFareValue>\\d+)\"\\s*(BidPrice=\"(?<BidPrice>\\d+)\")? Class=\"(?<ClsCode>[A-Z]{1})\" FareValue=\"(?<FareValue>\\d+)\".*$";
    
    
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
        
        if (lines != null)
        {
            for (String line : lines)
            {
                if (finalLines == null) { finalLines = new ArrayList<>(); }

                m = CcitinGSORegEx.RegExTest(startRegExStr, line);
                if (m.find()) { sectionLines = new ArrayList<>(); }

                m = CcitinGSORegEx.RegExTest(endRegExStr, line);
                if (m.find())
                {
                    if (sectionLines != null) {
                        if (trim) { sectionLines.add(line.trim()); }
                        else { sectionLines.add(line); }

                        finalLines.add(sectionLines);
                        sectionLines = null;
                    }
                }

                if (sectionLines != null) {
                    if (trim) { sectionLines.add(line.trim()); }
                    else { sectionLines.add(line); }
                }
            }

            if (finalLines != null) {
                if (finalLines.isEmpty()) { finalLines = null; }
            }
        }
        
        return finalLines;
    }
}
