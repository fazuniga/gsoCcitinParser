/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRQ;
import com.latam.sci.ptic.gso.auxiliar.CcitinGSORegEx;

import java.util.List;
import java.util.regex.Matcher;

/**
 *
 * @author 641515
 */
public class OTA_AirAvailRQParser {
    
    public OTA_AirAvailRQParser() { }
    
    public OTA_AirAvailRQ OTA_AirAvailRQProcessLines(List<String> OTA_AirAvailRQLines)
    {
        OTA_AirAvailRQ otaRQ = null;
        
        System.out.println("##### OTA AIR AVAIL RQ PARSING #####");
        Matcher m;
        
        if (OTA_AirAvailRQLines != null)
        {
            otaRQ = new OTA_AirAvailRQ();
            
            for (String line : OTA_AirAvailRQLines)
            {
                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.OTA_AirAvailRQ_Flight, line);
                if (m.find())
                {
                    String Charters = m.group("Charters");
                    String DirectOnly = m.group("DirectOnly");
                    String ExcludeCodeshares = m.group("ExcludeCodeshares");

                    otaRQ.setCharters(Charters);
                    otaRQ.setDirectOnly(DirectOnly);
                    otaRQ.setExcludeCodeshares(ExcludeCodeshares);
                } 

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.OTA_AirAvailRQ_times, line);
                if (m.find()) 
                {
                    String MinDeparture = m.group("MinDeparture");
                    String MaxDeparture = m.group("MaxDeparture");

                    otaRQ.setMinDeparture(MinDeparture);
                    otaRQ.setMaxDeparture(MaxDeparture);
                }

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.OTA_AirAvailRQ_Segment, line);
                if (m.find()) 
                {
                    String DepDate = m.group("DepDate");
                    otaRQ.setDepDate(DepDate);
                }

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.OTA_AirAvailRQ_Origin, line);
                if (m.find()) 
                {
                    String Origin = m.group("Origin");
                    otaRQ.setOrigin(Origin);
                }

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.OTA_AirAvailRQ_Destination, line);
                if (m.find()) 
                {
                    String Destination = m.group("Destination");
                    otaRQ.setDestination(Destination);
                }

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.OTA_AirAvailRQ_MoreAvailability, line);
                if (m.find()) 
                {
                    String MoreAvailability = m.group("MoreAvailability");
                    otaRQ.setMoreAvailability(MoreAvailability);
                }
            }
        }
        
        return otaRQ;
    }
}
