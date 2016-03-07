/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.InventoryRS;

import com.latam.sci.ptic.gso.auxiliar.SeatsClass;
import com.latam.sci.ptic.gso.auxiliar.CcitinGSORegEx;
import com.latam.sci.ptic.gso.auxiliar.CabinCapacity;
import com.latam.sci.ptic.gso.auxiliar.CabinNesting;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 *
 * @author 641515
 */
public class InventoryRSParser {

    public InventoryRSParser() { }
    
    public InventoryRS InventoryRSProcessLines(List<String> InventoryRSProcessLines)
    {
        InventoryRS invRS = null;
        
        List<List<String>> Flight = CcitinGSORegEx.IsolateSection(InventoryRSProcessLines, 
                CcitinGSORegEx.InventoryRS_Flight_start, 
                CcitinGSORegEx.InventoryRS_Flight_end);
        
        List<List<String>> Segments = CcitinGSORegEx.IsolateSection(InventoryRSProcessLines, 
                CcitinGSORegEx.InventoryRS_FlightSegment_start, 
                CcitinGSORegEx.InventoryRS_FlightSegment_end);
        
        List<List<String>> Legs = CcitinGSORegEx.IsolateSection(InventoryRSProcessLines, 
                CcitinGSORegEx.InventoryRS_FlightLeg_start, 
                CcitinGSORegEx.InventoryRS_FlightLeg_end);
        
        System.out.println("##### InventoryRS PARSING #####");
        Matcher m;
        
        // Flight
        if (Flight != null)
        {
            List<String> flightHeader = Flight.get(0); 
        
            // If the section "Flight" is defined, a new element is created
            invRS = new InventoryRS();

            for (String line : flightHeader)
            {
               m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightCarrier, line);
               if (m.find()) {
                   String FlightCarrier = m.group("FlightCarrier");
                   invRS.setFlightCarrier(FlightCarrier);
               }

               m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightNumber, line);
               if (m.find()) {
                   String FlightNumber = m.group("FlightNumber");
                   invRS.setFlightNumber(FlightNumber);
               }

               m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightDate, line);
               if (m.find()) {
                   String FlightDate = m.group("FlightDate");
                   invRS.setFlightDate(FlightDate);
               }
            }
        }
        
        if (invRS != null)
        {
            // Segments
            List<InventoryRS_FlightSegment> FlightSegments = ProcessSegments(Segments);
            invRS.setFlightSegments(FlightSegments);
            
            // Legs
            List<InventoryRS_FlightLeg> FlightLegs = ProcessLegs(Legs);
            invRS.setFlightLegs(FlightLegs);
        }
        
        return invRS;
    }
    
    public List<InventoryRS_FlightSegment> ProcessSegments(List<List<String>> Segments) {
        
        Matcher m;
        List<InventoryRS_FlightSegment> FlightSegments = new ArrayList<>();

        if (Segments != null)
        {
            for (List<String> segment : Segments)
            {
                InventoryRS_FlightSegment currentFlightSeg = new InventoryRS_FlightSegment();

                for (String line : segment)
                {
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightSegment_Origin, line);
                    if (m.find()) {
                        String SegOrgn = m.group("Origin");
                        currentFlightSeg.setSegOrgn(SegOrgn);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightSegment_Destination, line);
                    if (m.find()) {
                        String SegDstn = m.group("Destination");
                        currentFlightSeg.setSegDstn(SegDstn);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightSegment_Date, line);
                    if (m.find()) {
                        String SegDate = m.group("FlightDate");
                        currentFlightSeg.setSegDate(SegDate);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightSegment_Number, line);
                    if (m.find()) {
                        String SegFltNum = m.group("SegFltNum");
                        currentFlightSeg.setSegFltNum(SegFltNum);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightSegment_NumLegs, line);
                    if (m.find()) {
                        String SegNumLegs = m.group("SegNumLegs");
                        currentFlightSeg.setNumLegsThrough(Integer.parseInt(SegNumLegs));
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightSegment_DepTime, line);
                    if (m.find()) {
                        String SegDepTime = m.group("DepTime");
                        currentFlightSeg.setSegDepTime(SegDepTime);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightSegment_DepDateAdj, line);
                    if (m.find()) {
                        String SegDptDateAdj = m.group("DptDateAdj");
                        currentFlightSeg.setSegDepDateAdj(SegDptDateAdj);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightSegment_ArrTime, line);
                    if (m.find()) {
                        String SegArrTime = m.group("ArrTime");
                        currentFlightSeg.setSegArrTime(SegArrTime);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightSegment_ArrDateAdj, line);
                    if (m.find()) {
                        String SegArrDateAdj = m.group("ArrDateAdj");
                        currentFlightSeg.setSegArrDateAdj(SegArrDateAdj);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightSegment_FareClasses, line);
                    if (m.find()) {
                        String FareClasses = m.group("FareClasses");
                        currentFlightSeg.setFareClasses(FareClasses);
                    }
                }

                List<List<String>> SegSeatsSold = CcitinGSORegEx.IsolateSection(
                        segment, 
                        CcitinGSORegEx.InventoryRS_FlightSegment_SeatsSold_start,
                        CcitinGSORegEx.InventoryRS_FlightSegment_SeatsSold_end);

                // Seats Sold - It should appear just once
                if (SegSeatsSold != null) {
                    List<SeatsClass> SeatsSold = ProcessSeatsClass(SegSeatsSold.get(0));
                    currentFlightSeg.setSeatsSold(SeatsSold);
                }

                List<List<String>> SegGroupCounts = CcitinGSORegEx.IsolateSection(
                        segment, 
                        CcitinGSORegEx.InventoryRS_FlightSegment_GroupCounts_start,
                        CcitinGSORegEx.InventoryRS_FlightSegment_GroupCounts_end);

                // Group Counts - It should appear just once
                if (SegGroupCounts != null) {
                    List<SeatsClass> GroupCounts = ProcessSeatsClass(SegGroupCounts.get(0));
                    currentFlightSeg.setGroupCounts(GroupCounts);
                }

                List<List<String>> SegMaxSeats = CcitinGSORegEx.IsolateSection(
                        segment, 
                        CcitinGSORegEx.InventoryRS_FlightSegment_MaxSeats_start,
                        CcitinGSORegEx.InventoryRS_FlightSegment_MaxSeats_end);

                // MaxSeats - It should appear just once
                if (SegMaxSeats != null) {
                    List<SeatsClass> MaxSeats = ProcessSeatsClass(SegMaxSeats.get(0));
                    currentFlightSeg.setMaxSeats(MaxSeats);
                }

                FlightSegments.add(currentFlightSeg);
            }
        }
        return FlightSegments;
    }
    
    public List<InventoryRS_FlightLeg> ProcessLegs(List<List<String>> Legs) {
        
        Matcher m;
        List<InventoryRS_FlightLeg> FlightLegs = new ArrayList<>();

        if (Legs != null)
        {
            for (List<String> leg : Legs)
            {
                InventoryRS_FlightLeg currentFlightLeg = new InventoryRS_FlightLeg();

                /*  private String OperatingCrrCode;
                    private int OperatingFltNum;
                    private Boolean BidPriceFlag, CodeshareFlag; */

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
        }
        return FlightLegs;
    }
    
    public List<SeatsClass> ProcessSeatsClass(List<String> LinesSeatsClass) {
        Matcher m;
        List<SeatsClass> SeatsClassOut = new ArrayList<>();
        
        for (String line : LinesSeatsClass) 
        {
            m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightSegment_SeatsClass, line);

            if (m.find())
            {
                String ClsCode = m.group("ClsCode");
                int Amount = Integer.parseInt(m.group("Amount"));

                SeatsClass sc = new SeatsClass();
                sc.setClsCode(ClsCode);
                sc.setAmount(Amount);

                SeatsClassOut.add(sc);
            }
        }
        return SeatsClassOut;
    }
    
    public List<CabinCapacity> ProcessCabinCapacity(List<String> LinesCabinCapacities) {
        Matcher m;
        List<CabinCapacity> CabinCapacitiesOut = new ArrayList<>();
        
        for (String line : LinesCabinCapacities) 
        {
            m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightLeg_CabinCapacity, line);

            if (m.find())
            {
                int CabinIndex = Integer.parseInt(m.group("CabinIndex"));
                int PhysicalCapacity = Integer.parseInt(m.group("Capacity"));

                CabinCapacity cc = new CabinCapacity();
                cc.setCabinID(CabinIndex);
                cc.setPhysicalCapacity(PhysicalCapacity);

                CabinCapacitiesOut.add(cc);
            }
        }
        return CabinCapacitiesOut;
    }
    
    public List<CabinNesting> ProcessCabinNesting(List<String> LinesCabinNesting) {
        Matcher m;
        List<CabinNesting> CabinNestingOut = new ArrayList<>();
        
        for (String line : LinesCabinNesting) 
        {
            m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRS_FlightLeg_CabinNesting, line);

            if (m.find())
            {
                int CabinIndex = Integer.parseInt(m.group("CabinIndex"));
                String NestingOrder = m.group("NestingOrder");

                CabinNesting cn = new CabinNesting();
                cn.setCabinID(CabinIndex);
                cn.setFareClasses(NestingOrder);

                CabinNestingOut.add(cn);
            }
        }
        return CabinNestingOut;
    }
}
