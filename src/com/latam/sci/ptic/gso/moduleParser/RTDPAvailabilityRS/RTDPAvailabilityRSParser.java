/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRS;

import com.latam.sci.ptic.gso.auxiliar.Cabin;
import com.latam.sci.ptic.gso.auxiliar.CcitinGSORegEx;
import com.latam.sci.ptic.gso.auxiliar.Constants;
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
        Matcher m;
        
        // System.out.println("##### RTDPAvailabilityRS PARSING #####");
        
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
            }
            
            List<List<String>> Segments = CcitinGSORegEx.IsolateSection(
                    RTDPAvailabilityRSLines, 
                    CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_start, 
                    CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_end);
            rtdpRSSegments = ProcessSegments(Segments);
            
            List<List<String>> OnDInfos = CcitinGSORegEx.IsolateSection(
                    RTDPAvailabilityRSLines, 
                    CcitinGSORegEx.AirInventoryRTDPDisplayRS_OnDInfo_start, 
                    CcitinGSORegEx.AirInventoryRTDPDisplayRS_OnDInfo_end);
            
            List<RTDPAvailabilityRS_OnDInfo> OnDInfoaux = null;
            for (List<String> OnDInfo : OnDInfos)
            {
                if (OnDInfoaux == null) { OnDInfoaux = new ArrayList<>(); }
                OnDInfoaux.add(ProcessOnDInfo(OnDInfo));
            }
            
            // Finish the element
            rtdpRS.setOnDInfos(OnDInfoaux);
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
        
        if (Legs != null)
        {
            for (List<String> leg : Legs)
            {
                RTDPAvailabilityRS_Leg currentFlightLeg = new RTDPAvailabilityRS_Leg();

                for (String line : leg)
                {
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_Leg_start, line);
                    if (m.find()) {
                        String AirlineCode = m.group("AirlineCode");
                        String FlightNumber = m.group("FlightNumber");
                        String RTDPActive = m.group("RTDPActive");
                        String ArrivalDateAdj = m.group("ArrivalDateAdj");
                        String ArrivalTime = m.group("ArrivalTime");
                        String DepartureDate = m.group("DepartureDate");
                        String DepartureTime = m.group("DepartureTime");
                        String Origin = m.group("Origin");
                        String Destination = m.group("Destination");
                        String LegCabins = m.group("LegCabins");
                        String FareCabins = m.group("FareCabins");
                        
                        currentFlightLeg.setAirlineCode(AirlineCode);
                        currentFlightLeg.setFlightNumber(FlightNumber);
                        currentFlightLeg.setRTDPActive(RTDPActive);
                        currentFlightLeg.setArrivalDateAdj(ArrivalDateAdj);
                        currentFlightLeg.setArrivalTime(ArrivalTime);
                        currentFlightLeg.setDepartureDate(DepartureDate);
                        currentFlightLeg.setDepartureTime(DepartureTime);
                        currentFlightLeg.setOrigin(Origin);
                        currentFlightLeg.setDestination(Destination);
                        currentFlightLeg.setLegCabins(LegCabins);
                        currentFlightLeg.setFareCabins(FareCabins);
                    }
                }

                List<List<String>> LegCabinCapacities = CcitinGSORegEx.IsolateSection(
                        leg, 
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Leg_CabinCapacities_start,
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Leg_CabinCapacities_end);

                // LegCabinCapacities - It should appear just once
                if (LegCabinCapacities != null) {
                    List<Cabin> CabinCapacities = ProcessCabin(LegCabinCapacities.get(0));
                    currentFlightLeg.setCabinCapacities(CabinCapacities);
                }

                List<List<String>> LegCabinAuth = CcitinGSORegEx.IsolateSection(
                        leg, 
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Leg_CabinAuth_start,
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Leg_CabinAuth_end);

                // LegCabinAuth - It should appear just once
                if (LegCabinAuth != null) {
                    List<Cabin> CabinAuth = ProcessCabin(LegCabinAuth.get(0));
                    currentFlightLeg.setCabinAuth(CabinAuth);
                }
                
                List<List<String>> LegCabinSeatsSold = CcitinGSORegEx.IsolateSection(
                        leg, 
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Leg_CabinSeatsSold_start,
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Leg_CabinSeatsSold_end);

                // LegCabinSeatsSold - It should appear just once
                if (LegCabinSeatsSold != null) {
                    List<Cabin> CabinSeatsSold = ProcessCabin(LegCabinSeatsSold.get(0));
                    currentFlightLeg.setCabinSeatsSold(CabinSeatsSold);
                }
                
                List<List<String>> LegCabinSeatsAvail = CcitinGSORegEx.IsolateSection(
                        leg, 
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Leg_CabinSeatsSold_start,
                        CcitinGSORegEx.AirInventoryRTDPDisplayRS_Leg_CabinSeatsSold_end);

                // LegCabinSeatsAvail - It should appear just once
                if (LegCabinSeatsAvail != null) {
                    List<Cabin> CabinSeatsAvail = ProcessCabin(LegCabinSeatsAvail.get(0));
                    currentFlightLeg.setCabinSeatsAvail(CabinSeatsAvail);
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
    
    public List<Cabin> ProcessCabin(List<String> LinesCabin) {
        Matcher m;
        List<Cabin> CabinOut = new ArrayList<>();
        
        for (String line : LinesCabin) 
        {
            m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRS_Segment_CabinData, line);

            if (m.find())
            {
                int CabinIndex = Integer.parseInt(m.group("CabinIndex"));
                int Amount = Integer.parseInt(m.group("Amount"));

                Cabin cc = new Cabin();
                cc.setCabinID(CabinIndex);
                cc.setQuantity(Amount);

                CabinOut.add(cc);
            }
        }
        return CabinOut;
    }

    public RTDPAvailabilityRS_OnDInfo ProcessOnDInfo(List<String> OnDInfo) {
        Matcher m;
        RTDPAvailabilityRS_OnDInfo OnDInfoOut = null;
        
        if (OnDInfo != null)
        {
            OnDInfoOut = new RTDPAvailabilityRS_OnDInfo();
            List<Integer> SegIndices = null;
            List<RTDPAvailabilityRS_Fares> Fares = null;
            
            for (String line : OnDInfo) 
            {
                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRS_OnDInfo_start, line);
                if (m.find())
                {
                    String Origin = m.group("Origin");
                    String Destination = m.group("Destination");

                    OnDInfoOut.setOrigin(Origin);
                    OnDInfoOut.setDestination(Destination);
                }
                
                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRS_OnDInfo_SegIndex, line);
                if (m.find())
                {
                    if (SegIndices == null) { SegIndices = new ArrayList<>(); }
                    int SegIndex = Integer.parseInt(m.group("SegIndex"));
                    SegIndices.add(SegIndex);
                }
                
                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.AirInventoryRTDPDisplayRS_OnDInfo_Fares, line);
                if (m.find())
                {
                    if (Fares == null) { Fares = new ArrayList<>(); }
                    
                    String ClsCode = m.group("ClsCode");
                    int BidPrice;
                    try {
                        BidPrice = Integer.parseInt(m.group("BidPrice"));
                    }
                    catch (Exception e) {
                        BidPrice = Constants.getBID_PRICE_ERROR_VALUE();
                    }
                    int AdjustedFareValue = Integer.parseInt(m.group("AdjustedFareValue"));
                    int FareValue = Integer.parseInt(m.group("FareValue"));
                    
                    RTDPAvailabilityRS_Fares Fare = new RTDPAvailabilityRS_Fares();
                    Fare.setAdjustedFareValue(AdjustedFareValue);
                    Fare.setBidPrice(BidPrice);
                    Fare.setClsCode(ClsCode);
                    Fare.setFareValue(FareValue);

                    Fares.add(Fare);
                }
            }
            
            OnDInfoOut.setSegIndex(SegIndices);
            OnDInfoOut.setFares(Fares);
        }
        return OnDInfoOut;        
    }

}
