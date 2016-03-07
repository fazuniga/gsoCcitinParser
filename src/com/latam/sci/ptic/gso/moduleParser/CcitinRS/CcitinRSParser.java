/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.CcitinRS;

import com.latam.sci.ptic.gso.auxiliar.CcitinGSORegEx;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
// import static moduleParser.CcitinRS.CcitinRSElement.CcitinRS_dir_data;
// import static moduleParser.CcitinRS.CcitinRSElement.CcitinRS_dir_end;
// import static moduleParser.CcitinRS.CcitinRSElement.CcitinRS_dir_start;

/*
 * @author 641515
 */
public class CcitinRSParser {
    
    public CcitinRSParser()
    {
        
    }
}
    /*
    @SuppressWarnings("null")
    
    public CcitinRS CcitinRSProcessLines(List<String> cRSLines)
    {
        CcitinRS cRS = new CcitinRS();
        List<CcitinRSElement> cRSElements = new ArrayList<CcitinRSElement>();
        CcitinRSElement cRSe = null;
        
        System.out.println("##### CCITIN RS PARSING #####");
        Matcher m;
        
        for (String line : cRSLines)
        {
            System.out.println("LINEA: " + line);
            if (cRSe == null) { cRSe = new CcitinRSElement(); }
            
            m = CcitinGSORegEx.RegExTest(CcitinRS_dir_start, line);
            if (m.find())
            {
                String dirID = m.group("dirID");
                String dirOrigin = m.group("Origin");
                String dirDestination = m.group("Destination");
                String dirCityPOS = m.group("cityPOS");
                String dirCountryPOS = m.group("countryPOS");
                String directFlights = m.group("directFlights");
                String allowOAL = m.group("allowOAL");

                cRSe.setDirID(dirID);
                cRSe.setOrigin(dirOrigin);
                cRSe.setDestination(dirDestination);
                cRSe.setCityPOS(dirCityPOS);
                cRSe.setCountryPOS(dirCountryPOS);
                cRSe.setDirectFlights(directFlights);
                cRSe.setAllowOAL(allowOAL);
            } 
            
            m = CcitinGSORegEx.RegExTest(CcitinRS_dir_data, line);
            if (m.find()) 
            {
                String startDate = m.group("startDate");
                String endDate = m.group("endDate");
                String frequency = m.group("frequency");
                String startTime = m.group("startTime");
                String endTime = m.group("endTime");
                String orientation = m.group("orientation");

                cRSe.setStartDate(startDate);
                cRSe.setEndDate(endDate);
                cRSe.setFrequency(frequency);
                cRSe.setStartTime(startTime);
                cRSe.setEndTime(endTime);
                cRSe.setOrientation(orientation);
            }
            
            m = CcitinGSORegEx.RegExTest(CcitinRS_dir_end, line);
            if (m.find())
            {
                // After finding the final of a section, I add the element to the list
                if (cRSe.isDirectionComplete() && cRSe != null) {
                    cRSElements.add(cRSe);
                    cRSe = null;
                }
            }
        }
        
        cRS.setCcitinRS(cRSElements);        
        return cRS;        
    }
    */