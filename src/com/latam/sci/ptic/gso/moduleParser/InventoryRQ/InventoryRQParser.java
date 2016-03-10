/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.InventoryRQ;

import com.latam.sci.ptic.gso.auxiliar.CcitinGSORegEx;
import java.util.List;
import java.util.regex.Matcher;

/**
 *
 * @author 641515
 */
public class InventoryRQParser {
    
    public InventoryRQ InventoryRQProcessLines(List<String> InventoryRQProcessLines)
    {
        InventoryRQ invRQ = null;
        
        // System.out.println("##### InventoryRQ PARSING #####");
        Matcher m;
        
        if (InventoryRQProcessLines != null)
        {
            invRQ = new InventoryRQ();
            for (String line : InventoryRQProcessLines)
            {
                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRQ_FlightCarrier, line);
                if (m.find())
                {
                    String FlightCarrier = m.group("FlightCarrier");
                    invRQ.setFlightCarrier(FlightCarrier);
                }

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRQ_FlightNumber, line);
                if (m.find())
                {
                    String FlightNumber = m.group("FlightNumber");
                    invRQ.setFlightNumber(FlightNumber);
                }

                m = CcitinGSORegEx.RegExTest(CcitinGSORegEx.InventoryRQ_FlightDate, line);
                if (m.find())
                {
                    String FlightDate = m.group("FlightDate");
                    invRQ.setFlightDate(FlightDate);
                }
            }
        }
        
        return invRQ;
    }
}
