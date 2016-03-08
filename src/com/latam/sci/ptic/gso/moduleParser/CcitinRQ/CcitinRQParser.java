/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.CcitinRQ;

import com.latam.sci.ptic.gso.auxiliar.CcitinGSORegEx;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 *
 * @author 641515
 */
public class CcitinRQParser {
    
    public CcitinRQParser() {}
    
    public CcitinRQ CcitinRQProcessLines(List<String> CcitinRQLines)
    {
        CcitinRQ cRQ = null;
        
        System.out.println("##### CCITIN RQ PARSING #####");
        Matcher m;
        
        if (CcitinRQLines != null) 
        {
            cRQ = new CcitinRQ();
            List<CcitinRQDirection> Directions = new ArrayList<>();
            CcitinRQDirection cRQDir = null;
            
            for (String line : CcitinRQLines)
            {
                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.CcitinRQ_dir_start, line);
                if (m.find())
                {
                    cRQDir = new CcitinRQDirection();
                    
                    String DirectionID = m.group("DirectionID");
                    String dirOrigin = m.group("Origin");
                    String dirDestination = m.group("Destination");
                    String dirCityPOS = m.group("cityPOS");
                    String dirCountryPOS = m.group("countryPOS");
                    String directFlights = m.group("directFlights");
                    String allowOAL = m.group("allowOAL");

                    cRQDir.setDirectionID(DirectionID);
                    cRQDir.setOrigin(dirOrigin);
                    cRQDir.setDestination(dirDestination);
                    cRQDir.setCityPOS(dirCityPOS);
                    cRQDir.setCountryPOS(dirCountryPOS);
                    cRQDir.setDirectFlights(directFlights);
                    cRQDir.setAllowOAL(allowOAL);
                } 

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.CcitinRQ_dir_data, line);
                if (m.find()) 
                {
                    String startDate = m.group("startDate");
                    String endDate = m.group("endDate");
                    String frequency = m.group("frequency");
                    String startTime = m.group("startTime");
                    String endTime = m.group("endTime");
                    String orientation = m.group("orientation");

                    cRQDir.setStartDate(startDate);
                    cRQDir.setEndDate(endDate);
                    cRQDir.setFrequency(frequency);
                    cRQDir.setStartTime(startTime);
                    cRQDir.setEndTime(endTime);
                    cRQDir.setOrientation(orientation);
                    
                    Directions.add(cRQDir);
                }
            }
            
            cRQ.setDirections(Directions);
        }
        
        return cRQ;
    }
}
