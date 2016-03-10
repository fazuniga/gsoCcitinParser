/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.main;

import com.latam.sci.ptic.gso.auxiliar.Constants;
import com.latam.sci.ptic.gso.auxiliar.SeatsClass;
import com.latam.sci.ptic.gso.moduleParser.CcitinRS.CcitinRSDirection_OptionFlight_Seg_Cmp_Class;
import com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRS;
import com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRS.RTDPAvailabilityRS;
import com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRS.RTDPAvailabilityRS_OnDInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 * @author 641515
 */
public class CcitinParserProcess {
    
    private File[] files;
    private ProcessTestFile processTestFile;
    private List<CcitinParserResponse> cprList;
    
    public CcitinParserProcess() { }
    
    public void RunParser() throws Exception {
        // 0. Initialize Class that reads and processes the files
        processTestFile = new ProcessTestFile();
        cprList = new ArrayList<>();
        
        // 1. Get File List
        //  - getFileFormat defines the extension of the files
        //  - getInputFilesFolder determines the input folder
        files = Constants.getInputFiles(Constants.getInputFilesFolder(), Constants.getFileFormat());
        
        System.out.println("---------------------");
        System.out.println("#### Input Files ####");
        System.out.println("---------------------");
        
        for (File file : files)
        {
            System.out.println("##\tProcesando:\t" + file.getName());
            
            CcitinParserResponse cpr = processTestFile.processFile(file);
            if (cpr != null) { cprList.add(cpr); }
        }
        
        validateResponses(cprList);
    }
    
    public void validateResponses(List<CcitinParserResponse> cprList) {
        
        System.out.println("--------------------");
        System.out.println("#### Validation ####");
        System.out.println("--------------------");
        
        for (CcitinParserResponse cpr : cprList)
        {
            cpr.ccitinRQ.printCcitinRQ();
            
            // OTA_AirAvailRS
            for (OTA_AirAvailRS otaRS : cpr.getOTA_AirAvailRSList())
            {
                //
            
            
            }
            
            for (RTDPAvailabilityRS rtdpRS : cpr.rtdpAvailabilityRS)
            {
                for (RTDPAvailabilityRS_OnDInfo OnDInfo : rtdpRS.getOnDInfos())
                {
                    System.out.println("## O&D: " + OnDInfo.getOrigin() + " / " + OnDInfo.getDestination());
                    
                    Boolean RTDPControlled = false;
                    for (Integer SegIndex : OnDInfo.getSegIndex())
                    {
                        String segRTDPActive = rtdpRS.getSegments().get(SegIndex - 1).getRTDPActive();
                        Boolean RTDPActive = (segRTDPActive == "true") ? true : false;
                        RTDPControlled = RTDPControlled && RTDPActive;
                    }
                    
                    System.out.println("## Disponibilidad: " + ((RTDPControlled) ? "BP" : "AU"));
                    
                    // Choose the right availability
                    List<List<SeatsClass>> Availability = new ArrayList<>();
                    for (Integer SegIndex : OnDInfo.getSegIndex())
                    {
                        if (RTDPControlled) {
                            Availability.add(rtdpRS.getSegments().get(SegIndex - 1).getRTDPAvail());
                        } else {
                            Availability.add(rtdpRS.getSegments().get(SegIndex - 1).getSeatsAvail());
                        }
                    }
                    
                    // Final availability
                    List<CcitinRSDirection_OptionFlight_Seg_Cmp_Class> Classes = cpr.ccitinRS
                        .getDirections().get(0)
                        .getOptions().get(0)
                        .getFlights().get(0)
                        .getSegments().get(0)
                        .getCompartments().get(0)
                        .getClasses();
                    
                    for (CcitinRSDirection_OptionFlight_Seg_Cmp_Class Class : Classes)
                    {
                        System.out.println("Clase: " + Class.getClsCode() + " - Availability: " + Class.getOdAvailability());
                    }
                    
                    String a = "0";
                }
            }
        }
    }
}