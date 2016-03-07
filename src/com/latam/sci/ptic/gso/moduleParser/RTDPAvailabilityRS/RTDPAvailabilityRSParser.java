/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRS;

import com.latam.sci.ptic.gso.auxiliar.CcitinGSORegEx;
import com.latam.sci.ptic.gso.auxiliar.SeatsClass;
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
                
                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRS_RPIInfo, line);
                if (m.find())
                {
                    int NumberOfSeats = Integer.parseInt(m.group("NumberOfSeats"));

                    rtdpRS.setNumberOfSeats(NumberOfSeats);
                }

                

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRS_OnDInfo_start, line);
                if (m.find())
                {
                    String Origin = m.group("Origin");
                    String Destination = m.group("Destination");

                    rtdpRS.setOrigin(Origin);
                    rtdpRS.setDestination(Destination);
                }

                
            }
            // Finish the element
            rtdpRS.setSegments(rtdpRSSegments);
        }
        
        return rtdpRS;
    }
    
    public List<RTDPAvailabilityRS_Segment> ProcessSegments(List<List<String>> Segments) {
        Matcher m;
        List<RTDPAvailabilityRS_Segment> FlightSegments = new ArrayList<>();

        if (Segments != null)
        {
            for (List<String> segment : Segments)
            {
                RTDPAvailabilityRS_Segment currentFlightSeg = new RTDPAvailabilityRS_Segment();

                for (String line : segment)
                {
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_start, line);
                    if (m.find()) 
                    {
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

                        currentFlightSeg.setAction(Action);
                        currentFlightSeg.setAirlineCode(AirlineCode);
                        currentFlightSeg.setFlightNumber(FlightNumber);
                        currentFlightSeg.setArrivalDateAdj(ArrivalDateAdj);
                        currentFlightSeg.setArrivalTime(ArrivalTime);
                        currentFlightSeg.setDepartureDate(DepartureDate);
                        currentFlightSeg.setDepartureTime(DepartureTime);
                        currentFlightSeg.setOrigin(Origin);
                        currentFlightSeg.setDestination(Destination);
                        currentFlightSeg.setFareClasses(FareClasses);
                        currentFlightSeg.setNoOfLegs(NoOfLegs);
                        currentFlightSeg.setRTDPActive(RTDPActive);
                    }
                }

                List<List<String>> SegSeatsAvail = CcitinGSORegEx.IsolateSection(
                        segment, 
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_SeatsAvail_start,
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_SeatsAvail_end);

                // Seats Avail - It should appear just once
                if (SegSeatsAvail != null) {
                    List<SeatsClass> SeatsAvail = ProcessSeatsClass(SegSeatsAvail.get(0));
                    currentFlightSeg.setSeatsAvail(SeatsAvail);
                }
                
                List<List<String>> SegRTDPAvail = CcitinGSORegEx.IsolateSection(
                        segment, 
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_RTDPAvail_start,
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_RTDPAvail_end);

                // RTDPAvail - It should appear just once
                if (SegRTDPAvail != null) {
                    List<SeatsClass> RTDPAvail = ProcessSeatsClass(SegRTDPAvail.get(0));
                    currentFlightSeg.setRTDPAvail(RTDPAvail);
                }

                List<List<String>> SegSeatsSold = CcitinGSORegEx.IsolateSection(
                        segment, 
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_SeatsSold_start,
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_SeatsSold_end);
                
                // SeatsSold - It should appear just once
                if (SegSeatsSold != null) {
                    List<SeatsClass> SeatsSold = ProcessSeatsClass(SegSeatsSold.get(0));
                    currentFlightSeg.setSeatsAvail(SeatsSold);
                }

                List<List<String>> SegLegs = CcitinGSORegEx.IsolateSection(
                        segment, 
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_Leg_start, 
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_Leg_end);
                
                List<RTDPAvailabilityRS_Leg> Legs = ProcessLegs(SegLegs);
                currentFlightSeg.setLegs(Legs);

                FlightSegments.add(currentFlightSeg);
            }
        }
        return FlightSegments;
    }
    
    public List<RTDPAvailabilityRS_Leg> ProcessLegs(List<List<String>> Legs) {
        
        Matcher m;
        List<RTDPAvailabilityRS_Leg> FlightLegs = new ArrayList<>();
        /*
        if (Legs != null)
        {
            for (List<String> leg : Legs)
            {
                RTDPAvailabilityRS_Leg currentFlightLeg = new RTDPAvailabilityRS_Leg();

                for (String line : leg)
                {
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightLeg_Origin, line);
                    if (m.find()) {
                        String SegOrgn = m.group("Origin");
                        currentFlightLeg.setLegOrgn(SegOrgn);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightLeg_Destination, line);
                    if (m.find()) {
                        String SegDstn = m.group("Destination");
                        currentFlightLeg.setLegDstn(SegDstn);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightLeg_Date, line);
                    if (m.find()) {
                        String SegDate = m.group("FlightDate");
                        currentFlightLeg.setLegDate(SegDate);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightLeg_DepTime, line);
                    if (m.find()) {
                        String LegDepTime = m.group("DepTime");
                        currentFlightLeg.setLegDepTime(LegDepTime);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightLeg_ArrTime, line);
                    if (m.find()) {
                        String LegArrTime = m.group("ArrTime");
                        currentFlightLeg.setLegArrTime(LegArrTime);
                    }

                    // These fields may not be present
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightSegment_DepDateAdj, line);
                    if (m.find()) {
                        String LegDptDateAdj = m.group("DptDateAdj");
                        currentFlightLeg.setLegDepDateAdj(LegDptDateAdj);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightSegment_ArrDateAdj, line);
                    if (m.find()) {
                        String LegArrDateAdj = m.group("ArrDateAdj");
                        currentFlightLeg.setLegArrDateAdj(LegArrDateAdj);
                    }
                }

                List<List<String>> LegSeatsSold = CcitinGSORegEx.IsolateSection(
                        leg, 
                        CcitinGSORegEx.InventoryRS_FlightLeg_SeatsSold_start,
                        CcitinGSORegEx.InventoryRS_FlightLeg_SeatsSold_end);

                // Seats Sold - It should appear just once
                if (LegSeatsSold != null) {
                    List<SeatsClass> SeatsSold = ProcessSeatsClass(LegSeatsSold.get(0));
                    currentFlightLeg.setSeatsSold(SeatsSold);
                }

                List<List<String>> LegAuthorization = CcitinGSORegEx.IsolateSection(
                        leg, 
                        CcitinGSORegEx.InventoryRS_FlightLeg_LegAuth_start,
                        CcitinGSORegEx.InventoryRS_FlightLeg_LegAuth_end);

                // Leg Authorization - It should appear just once
                if (LegAuthorization != null) {
                    List<SeatsClass> LegAuth = ProcessSeatsClass(LegAuthorization.get(0));
                    currentFlightLeg.setLegAuthorization(LegAuth);
                }

                List<List<String>> LegCabinCapacities = CcitinGSORegEx.IsolateSection(
                        leg, 
                        CcitinGSORegEx.InventoryRS_FlightLeg_Capacities_start,
                        CcitinGSORegEx.InventoryRS_FlightLeg_Capacities_end);

                // CabinCapacities - It should appear just once
                if (LegCabinCapacities != null) {
                    List<CabinCapacity> Capacities = ProcessCabinCapacity(LegCabinCapacities.get(0));
                    currentFlightLeg.setCabinCapacities(Capacities);
                }

                List<List<String>> LegCabinNesting = CcitinGSORegEx.IsolateSection(
                        leg, 
                        CcitinGSORegEx.InventoryRS_FlightLeg_CabinNesting_start,
                        CcitinGSORegEx.InventoryRS_FlightLeg_CabinNesting_end);

                // CabinCapacities - It should appear just once
                if (LegCabinNesting != null) {
                    List<CabinNesting> CabinNestings = ProcessCabinNesting(LegCabinNesting.get(0));
                    currentFlightLeg.setCabinNesting(CabinNestings);
                }

                FlightLegs.add(currentFlightLeg);
            }
        }*/
        return FlightLegs;
    }
    
    public List<SeatsClass> ProcessSeatsClass(List<String> LinesSeatsClass) {
        Matcher m;
        List<SeatsClass> SeatsClassOut = new ArrayList<>();
        
        for (String line : LinesSeatsClass) 
        {
            m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_ClassAvailability, line);

            if (m.find())
            {
                String ClsCode = m.group("ClsCode");
                int Availability = Integer.parseInt(m.group("Availability"));

                SeatsClass sc = new SeatsClass();
                sc.setClsCode(ClsCode);
                sc.setAmount(Availability);

                SeatsClassOut.add(sc);
            }
        }
        return SeatsClassOut;
    }
}
