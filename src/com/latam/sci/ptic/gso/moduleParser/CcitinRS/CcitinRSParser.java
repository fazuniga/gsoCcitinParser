/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.CcitinRS;
// import static moduleParser.CcitinRS.CcitinRSElement.CcitinRS_dir_end;
// import static moduleParser.CcitinRS.CcitinRSElement.CcitinRS_dir_start;

import com.latam.sci.ptic.gso.auxiliar.CcitinGSORegEx;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;


/*
 * @author 641515
 */
public class CcitinRSParser {
    
    public CcitinRSParser() { }
    
    public CcitinRS CcitinRSProcessLines(List<String> CcitinRSLines)
    {
        CcitinRS ccitinRS = null;
        
        // System.out.println("##### CCITIN RS PARSING #####");
        Matcher m;
        
        if (CcitinRSLines != null)
        {
            ccitinRS = new CcitinRS();
            List<CcitinRSDirection> directions = null;
            CcitinRSDirection direction = null;
            
            List<CcitinRSDirection_Option> options = null;
            CcitinRSDirection_Option option = null;
            
            List<CcitinRSDirection_OptionFlight> flights = null;
     
            List<List<String>> Directions = CcitinGSORegEx.IsolateSection(
                    CcitinRSLines, 
                    CcitinGSORegEx.CcitinRS_Direction_start, 
                    CcitinGSORegEx.CcitinRS_Direction_end);
            
            if (Directions != null) 
            {
                // Initialize the array if there are directions in the response
                directions = new ArrayList<>();
                
                for (List<String> Direction : Directions)
                {
                    for (String line : Direction)
                    {
                        // General information related to current direction
                        m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.CcitinRS_Direction_start, line);
                        if (m.find())
                        {
                            int DirectionID = Integer.parseInt(m.group("DirectionID"));
                            
                            direction = new CcitinRSDirection();
                            direction.setDirectionID(DirectionID);
                        }
                    }
                    
                    List<List<String>> Options = CcitinGSORegEx.IsolateSection(
                        Direction, 
                        CcitinGSORegEx.CcitinRS_Direction_Option_start, 
                        CcitinGSORegEx.CcitinRS_Direction_Option_end);
                    
                    if (Options != null)
                    {
                        options = new ArrayList<>();
                        
                        for (List<String> Option : Options)
                        {
                            for (String line : Option)
                            {
                                // General information related to current option
                                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.CcitinRS_Direction_Option_start, line);
                                if (m.find())
                                {
                                    int EFT = Integer.parseInt(m.group("EFT"));
                                    int OptionID = Integer.parseInt(m.group("OptionID"));

                                    option = new CcitinRSDirection_Option();
                                    option.setEFT(EFT);
                                    option.setOptionID(OptionID);
                                }
                            }
                            
                            List<List<String>> Flights = CcitinGSORegEx.IsolateSection(
                                Option, 
                                CcitinGSORegEx.CcitinRS_Direction_OptionFlight_start, 
                                CcitinGSORegEx.CcitinRS_Direction_OptionFlight_end);
                            
                            flights = ProcessFlights(Flights);
                            if (flights != null) { option.setFlights(flights); }
                            options.add(option);
                        }
                    }
                    if (options != null) { direction.setOptions(options); }
                    directions.add(direction);
                }
                ccitinRS.setDirections(directions);
            }
        }
        
        return ccitinRS;
    }
    
    public List<CcitinRSDirection_OptionFlight> ProcessFlights(List<List<String>> Flights)
    {
        Matcher m;
        List<CcitinRSDirection_OptionFlight> flights = null;
        CcitinRSDirection_OptionFlight flight = null;
        
        if (Flights != null)
        {
            flights = new ArrayList<>();

            for (List<String> Flight : Flights)
            {
                for (String line : Flight)
                {
                    // General information related to current flight
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.CcitinRS_Direction_OptionFlight_start, line);
                    if (m.find())
                    {
                        int EFT = Integer.parseInt(m.group("EFT"));
                        String aircraftType = m.group("aircraftType");
                        String arrivalDate = m.group("arrivalDate");
                        String arrivalTime = m.group("arrivalTime");
                        String departureDate = m.group("departureDate");
                        String departureTime = m.group("departureTime");
                        String carrier = m.group("carrier");
                        String flightNumber = m.group("flightNumber");
                        String origin = m.group("origin");
                        String destination = m.group("destination");
                        int sequenceNumber = Integer.parseInt(m.group("sequenceNumber"));

                        flight = new CcitinRSDirection_OptionFlight();
                        flight.setEFT(EFT);
                        flight.setAircraftType(aircraftType);
                        flight.setArrivalDate(arrivalDate);
                        flight.setArrivalTime(arrivalTime);
                        flight.setDepartureDate(departureDate);
                        flight.setDepartureTime(departureTime);
                        flight.setCarrier(carrier);
                        flight.setFlightNumber(flightNumber);
                        flight.setOrigin(origin);
                        flight.setDestination(destination);
                        flight.setSequenceNumber(sequenceNumber);
                    }
                }

                List<List<String>> Legs = CcitinGSORegEx.IsolateSection(
                    Flight, 
                    CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Leg_start, 
                    CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Leg_end);

                // Process Legs
                List<CcitinRSDirection_OptionFlight_Leg> legs = ProcessLegs(Legs);
                flight.setLegs(legs);

                List<List<String>> Segs = CcitinGSORegEx.IsolateSection(
                    Flight, 
                    CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Seg_start, 
                    CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Seg_end);

                // Process Segs
                List<CcitinRSDirection_OptionFlight_Seg> segs = ProcessSegments(Segs);
                flight.setSegments(segs);

                flights.add(flight);
            }
        }
        return flights;
    }
    
    public List<CcitinRSDirection_OptionFlight_Leg> ProcessLegs(List<List<String>> Legs)
    {
        Matcher m;
        List<CcitinRSDirection_OptionFlight_Leg> legs = null;
        CcitinRSDirection_OptionFlight_Leg leg = null;
        
        if (Legs != null)
        {
            legs = new ArrayList<>();

            for (List<String> Leg : Legs)
            {
                for (String line : Leg)
                {
                    // General information related to current leg
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Leg_start, line);
                    if (m.find())
                    {
                        int allowBooking = Integer.parseInt(m.group("allowBooking"));
                        int allowGroup = Integer.parseInt(m.group("allowGroup"));
                        int allowSale = Integer.parseInt(m.group("allowSale"));
                        int allowWaitList = Integer.parseInt(m.group("allowWaitList"));
                        int dateOffset = Integer.parseInt(m.group("dateOffset"));
                        String LegDstn = m.group("LegDstn");
                        int sequenceNumber = Integer.parseInt(m.group("sequenceNumber"));

                        leg = new CcitinRSDirection_OptionFlight_Leg();
                        leg.setAllowBooking(allowBooking);
                        leg.setAllowGroup(allowGroup);
                        leg.setAllowSale(allowSale);
                        leg.setAllowWaitlist(allowWaitList);
                        leg.setDateOffset(dateOffset);
                        leg.setLegDstn(LegDstn);
                        leg.setLegSequenceNumber(sequenceNumber);
                    }                                                
                }

                List<List<String>> LegCmps = CcitinGSORegEx.IsolateSection(
                    Leg, 
                    CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Leg_Cmp_start, 
                    CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Leg_Cmp_end);

                List<CcitinRSDirection_OptionFlight_Leg_Cmp> legCmps = ProcessLegCmps(LegCmps);
                if (legCmps != null) { leg.setCompartments(legCmps); }
                
                legs.add(leg);
            }
        }
        
        return legs;
    }
    
    public List<CcitinRSDirection_OptionFlight_Leg_Cmp> ProcessLegCmps(List<List<String>> LegCmps)
    {
        Matcher m;
        List<CcitinRSDirection_OptionFlight_Leg_Cmp> legcmps = null;
        CcitinRSDirection_OptionFlight_Leg_Cmp legcmp = null;
        
        if (LegCmps != null)
        {
            legcmps = new ArrayList<>();

            for (List<String> LegCmp : LegCmps)
            {
                List<CcitinRSDirection_OptionFlight_Leg_Cmp_Class> Classes = null;

                for (String line : LegCmp)
                {
                    // General information related to current leg-cmp
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Leg_Cmp_start, line);
                    if (m.find())
                    {
                        int AdjustedCapacity = Integer.parseInt(m.group("AdjustedCapacity"));
                        int Capacity = Integer.parseInt(m.group("Capacity"));
                        String CmpCode = m.group("CmpCode");
                        int groupBooked = Integer.parseInt(m.group("groupBooked"));
                        int odFactor = Integer.parseInt(m.group("odFactor"));
                        int totalAU = Integer.parseInt(m.group("totalAU"));
                        int totalBooked = Integer.parseInt(m.group("totalBooked"));

                        legcmp = new CcitinRSDirection_OptionFlight_Leg_Cmp();

                        legcmp.setAdjustedCapacity(AdjustedCapacity);
                        legcmp.setCapacity(Capacity);
                        legcmp.setCmpCode(CmpCode);
                        legcmp.setGroupBooked(groupBooked);
                        legcmp.setOdFactor(odFactor);
                        legcmp.setTotalAU(totalAU);
                        legcmp.setTotalBooked(totalBooked);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Leg_Cmp_Class, line);
                    if (m.find())
                    {
                        if (Classes == null) { Classes = new ArrayList<>(); }

                        int allowBooking = Integer.parseInt(m.group("allowBooking"));
                        int allowGroup = Integer.parseInt(m.group("allowGroup"));
                        int allowSale = Integer.parseInt(m.group("allowSale"));
                        int allowWaitList = Integer.parseInt(m.group("allowWaitList"));
                        String ClsCode = m.group("ClsCode");
                        int groupBooked = Integer.parseInt(m.group("groupBooked"));
                        int totalBooked = Integer.parseInt(m.group("totalBooked"));

                        CcitinRSDirection_OptionFlight_Leg_Cmp_Class clase = new CcitinRSDirection_OptionFlight_Leg_Cmp_Class();

                        clase.setAllowBooking(allowBooking);
                        clase.setAllowGroup(allowGroup);
                        clase.setAllowSale(allowSale);
                        clase.setAllowWaitList(allowWaitList);
                        clase.setClsCode(ClsCode);
                        clase.setGroupBooked(groupBooked);
                        clase.setTotalBooked(totalBooked);

                        Classes.add(clase);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Leg_Cmp_end, line);
                    if (m.find())
                    {
                        legcmp.setClasses(Classes);
                    }
                }
                legcmps.add(legcmp);
            }
        }
        return legcmps;
    }
    
    public List<CcitinRSDirection_OptionFlight_Seg> ProcessSegments(List<List<String>> Segs)
    {
        Matcher m;
        List<CcitinRSDirection_OptionFlight_Seg> segs = null;
        CcitinRSDirection_OptionFlight_Seg seg = null;

        if (Segs != null)
        {
            segs = new ArrayList<>();
            
            for (List<String> Seg : Segs)
            {
                for (String line : Seg)
                {
                    // General information related to current seg
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Seg_start, line);
                    if (m.find())
                    {
                        int allowBooking = Integer.parseInt(m.group("allowBooking"));
                        int allowGroup = Integer.parseInt(m.group("allowGroup"));
                        int allowSale = Integer.parseInt(m.group("allowSale"));
                        int allowWaitList = Integer.parseInt(m.group("allowWaitList"));
                        String SegOrgn = m.group("SegOrgn");
                        String SegDstn = m.group("SegDstn");
                        int sequenceNumber = Integer.parseInt(m.group("sequenceNumber"));

                        seg = new CcitinRSDirection_OptionFlight_Seg();
                        seg.setAllowBooking(allowBooking);
                        seg.setAllowGroup(allowGroup);
                        seg.setAllowSale(allowSale);
                        seg.setAllowWaitlist(allowWaitList);
                        seg.setSegOrgn(SegOrgn);
                        seg.setSegDstn(SegDstn);
                        seg.setSegSequenceNumber(sequenceNumber);
                    }
                }

                List<List<String>> SegCmps = CcitinGSORegEx.IsolateSection(
                    Seg, 
                    CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Seg_Cmp_start, 
                    CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Seg_Cmp_end);

                List<CcitinRSDirection_OptionFlight_Seg_Cmp> segCmps = ProcessSegCmps(SegCmps);
                if (segCmps != null) { seg.setCompartments(segCmps); }

                segs.add(seg);
            }
        }
        
        return segs;
    }
    
    public List<CcitinRSDirection_OptionFlight_Seg_Cmp> ProcessSegCmps(List<List<String>> SegCmps)
    {
        Matcher m;
        List<CcitinRSDirection_OptionFlight_Seg_Cmp> segcmps = null;
        CcitinRSDirection_OptionFlight_Seg_Cmp segcmp = null;

        if (SegCmps != null)
        {
            segcmps = new ArrayList<>();
        
            for (List<String> SegCmp : SegCmps)
            {
                List<CcitinRSDirection_OptionFlight_Seg_Cmp_Class> Classes = null;

                for (String line : SegCmp)
                {
                    // General information related to current leg-cmp
                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Seg_Cmp_start, line);
                    if (m.find())
                    {
                        String CmpCode = m.group("CmpCode");
                        int groupBooked = Integer.parseInt(m.group("groupBooked"));

                        segcmp = new CcitinRSDirection_OptionFlight_Seg_Cmp();

                        segcmp.setCmpCode(CmpCode);
                        segcmp.setGroupBooked(groupBooked);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Seg_Cmp_Class, line);
                    if (m.find())
                    {
                        if (Classes == null) { Classes = new ArrayList<>(); }

                        int allowBooking = Integer.parseInt(m.group("allowBooking"));
                        int allowGroup = Integer.parseInt(m.group("allowGroup"));
                        int allowSale = Integer.parseInt(m.group("allowSale"));
                        int allowWaitList = Integer.parseInt(m.group("allowWaitList"));
                        String ClsCode = m.group("ClsCode");
                        int groupBooked = Integer.parseInt(m.group("groupBooked"));
                        int limit = Integer.parseInt(m.group("limit"));
                        int odAvailability = Integer.parseInt(m.group("odAvailability"));
                        int totalBooked = Integer.parseInt(m.group("totalBooked"));

                        CcitinRSDirection_OptionFlight_Seg_Cmp_Class clase = new CcitinRSDirection_OptionFlight_Seg_Cmp_Class();

                        clase.setAllowBooking(allowBooking);
                        clase.setAllowGroup(allowGroup);
                        clase.setAllowSale(allowSale);
                        clase.setAllowWaitList(allowWaitList);
                        clase.setClsCode(ClsCode);
                        clase.setGroupBooked(groupBooked);
                        clase.setLimit(limit);
                        clase.setOdAvailability(odAvailability);
                        clase.setTotalBooked(totalBooked);

                        Classes.add(clase);
                    }

                    m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.CcitinRS_Direction_OptionFlight_Leg_Cmp_end, line);
                    if (m.find())
                    {
                        segcmp.setClasses(Classes);
                    }
                }
                segcmps.add(segcmp);
            }
        }        
        
        return segcmps;
        
    }
}
    