/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.main.parser;

import com.latam.sci.ptic.gso.auxiliar.Constants;
import com.latam.sci.ptic.gso.auxiliar.CcitinGSORegEx;
import static com.latam.sci.ptic.gso.main.parser.ProcessTestFile.ModuleType.CcitinRQ;
import static com.latam.sci.ptic.gso.main.parser.ProcessTestFile.ModuleType.InventoryRS;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import com.latam.sci.ptic.gso.moduleParser.CcitinRQ.CcitinRQ;
import com.latam.sci.ptic.gso.moduleParser.CcitinRQ.CcitinRQParser;
import com.latam.sci.ptic.gso.moduleParser.InventoryRQ.InventoryRQ;
import com.latam.sci.ptic.gso.moduleParser.InventoryRQ.InventoryRQParser;
import com.latam.sci.ptic.gso.moduleParser.InventoryRS.InventoryRS;
import com.latam.sci.ptic.gso.moduleParser.InventoryRS.InventoryRSParser;
import com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRQ.OTA_AirAvailRQParser;
import com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRQ.OTA_AirAvailRQ;
import com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRS;
import com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS.OTA_AirAvailRSParser;
import com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRQ.RTDPAvailabilityRQ;
import com.latam.sci.ptic.gso.moduleParser.RTDPAvailabilityRQ.RTDPAvailabilityRQParser;

/**
 *
 * @author fazuniga
 */
public class ProcessTestFile {
    
    public CcitinParserResponse cpr = null;
    
    public ProcessTestFile() {
    
    }
    
    public CcitinParserResponse processFile(File file) throws Exception {
        cpr = new CcitinParserResponse();
        
        // Store the filename
        cpr.setFileName(file.getName());
        
        List<String> fileLines = ReadFile(file);
        List<List<String>> CcitinRQLines = CcitinGSORegEx.IsolateSection(fileLines, CcitinGSORegEx.CcitinRQ_start, CcitinGSORegEx.CcitinRQ_end);
        List<List<String>> CcitinRSLines = CcitinGSORegEx.IsolateSection(fileLines, CcitinGSORegEx.CcitinRS_start, CcitinGSORegEx.CcitinRS_end);
        List<List<String>> OTA_AirAvailRQLines = CcitinGSORegEx.IsolateSection(fileLines, CcitinGSORegEx.OTA_AirAvailRQ_start, CcitinGSORegEx.OTA_AirAvailRQ_end);
        List<List<String>> OTA_AirAvailRSLines = CcitinGSORegEx.IsolateSection(fileLines, CcitinGSORegEx.OTA_AirAvailRS_start, CcitinGSORegEx.OTA_AirAvailRS_end);
        List<List<String>> InventoryRQLines = CcitinGSORegEx.IsolateSection(fileLines, CcitinGSORegEx.InventoryRQ_start, CcitinGSORegEx.InventoryRQ_end);
        List<List<String>> InventoryRSLines = CcitinGSORegEx.IsolateSection(fileLines, CcitinGSORegEx.InventoryRS_start, CcitinGSORegEx.InventoryRS_end);
        List<List<String>> RTDPAvailabilityRQLines = CcitinGSORegEx.IsolateSection(fileLines, CcitinGSORegEx.AirInventoryRTDPDisplayRQ_start, CcitinGSORegEx.AirInventoryRTDPDisplayRQ_end);
        List<List<String>> RTDPAvailabilityRSLines = CcitinGSORegEx.IsolateSection(fileLines, CcitinGSORegEx.AirInventoryRTDPDisplayRS_start, CcitinGSORegEx.AirInventoryRTDPDisplayRS_end);
        
        if (CcitinRQLines != null) {
            if (CcitinRQLines.size() == 1) {
                ProcessSection(ModuleType.CcitinRQ, CcitinRQLines.get(0));
            } else {
                throw new Exception("[ERROR] CcitinRQ: Hay más de 1 CcitinRQ en el archivo - file:\t" + cpr.getFileName());
            }
        }
        
        if (CcitinRSLines != null) {
            if (CcitinRSLines.size() == 1) {
                ProcessSection(ModuleType.CcitinRS, CcitinRSLines.get(0));
            } else {
                throw new Exception("[ERROR] CcitinRS: Hay más de 1 CcitinRS en el archivo - file:\t" + cpr.getFileName());
            }
        }
        
        if (OTA_AirAvailRQLines != null) {
            for (List<String> OTA_AirAvailRQs : OTA_AirAvailRQLines) {
                ProcessSection(ModuleType.OTA_AirAvailRQ, OTA_AirAvailRQs);
            }
        }
        
        if (OTA_AirAvailRSLines != null) {
            for (List<String> OTA_AirAvailRSs : OTA_AirAvailRSLines) {
                ProcessSection(ModuleType.OTA_AirAvailRS, OTA_AirAvailRSs);
            }
        }
        
        if (InventoryRQLines != null) {
            for (List<String> InventoryRQs : InventoryRQLines) {
                ProcessSection(ModuleType.InventoryRQ, InventoryRQs);
            }
        }
        
        if (InventoryRSLines != null) {
            for (List<String> InventoryRSs : InventoryRSLines) {
                ProcessSection(ModuleType.InventoryRS, InventoryRSs);
            }
        }
        
        if (RTDPAvailabilityRQLines != null) {
            for (List<String> RTDPAvailabilityRQs : RTDPAvailabilityRQLines) {
                ProcessSection(ModuleType.RTDPAvailabilityRQ, RTDPAvailabilityRQs);
            }
        }
        
        if (RTDPAvailabilityRSLines != null) {
            for (List<String> RTDPAvailabilityRSs : RTDPAvailabilityRSLines) {
                ProcessSection(ModuleType.RTDPAvailabilityRS, RTDPAvailabilityRSs);
            }
        }
        
        return cpr;
    }
    
    private List<String> ReadFile(File file) {
        
        List<String> fileLines = new ArrayList<>();
        String line;
        
        try {
            BufferedReader reader = Files.newBufferedReader(file.toPath(), Constants.getProjectEncoding());
            while ((line = reader.readLine()) != null) { fileLines.add(line); }
        }
        catch (IOException e) {
            System.out.println("[ERROR] No fue posible leer el archivo:\t" + file.getName());
        }
        
        return fileLines;
    }
    
    private void ProcessSection(ModuleType moduleType, List<String> sectionLines)
    {
        try {
            if (moduleType != null)
            {
                if (!Constants.testing) {
                    System.out.println("##### Section: " + moduleType.toString() + " #####");

                    for (String sectionLine : sectionLines) {
                        System.out.println("\tSection Line:\t" + sectionLine);
                    }
                }
                
                switch (moduleType) {
                    case CcitinRQ:
                        CcitinRQParser cRQparser = new CcitinRQParser();
                        CcitinRQ cRQ = cRQparser.CcitinRQProcessLines(sectionLines);
                        cpr.ccitinRQ = cRQ;
                        break;
                    case CcitinRS:
                        break;
                    case OTA_AirAvailRQ:
                        OTA_AirAvailRQParser otaRQparser = new OTA_AirAvailRQParser();
                        OTA_AirAvailRQ otaRQ = otaRQparser.OTA_AirAvailRQProcessLines(sectionLines);
                        if (cpr.OTA_AirAvailRQList == null) { cpr.OTA_AirAvailRQList = new ArrayList<>(); }
                        cpr.OTA_AirAvailRQList.add(otaRQ);
                        break;
                    case OTA_AirAvailRS:
                        OTA_AirAvailRSParser otaRSparser = new OTA_AirAvailRSParser();
                        OTA_AirAvailRS otaRS = otaRSparser.OTA_AirAvailRSProcessLines(sectionLines);
                        if (cpr.OTA_AirAvailRSList == null) { cpr.OTA_AirAvailRSList = new ArrayList<>(); }
                        cpr.OTA_AirAvailRSList.add(otaRS);                        
                        break;
                    case InventoryRQ:
                        InventoryRQParser iRQparser = new InventoryRQParser();
                        InventoryRQ iRQ = iRQparser.InventoryRQProcessLines(sectionLines);
                        if (cpr.InventoryRQList == null) { cpr.InventoryRQList = new ArrayList<>(); }
                        cpr.InventoryRQList.add(iRQ);
                        break;
                    case InventoryRS:
                        InventoryRSParser iRSparser = new InventoryRSParser();
                        InventoryRS iRS = iRSparser.InventoryRSProcessLines(sectionLines);
                        if (cpr.InventoryRSList == null) { cpr.InventoryRSList = new ArrayList<>(); }
                        cpr.InventoryRSList.add(iRS);
                        break;
                    case RTDPAvailabilityRQ:
                        RTDPAvailabilityRQParser rtdpRQparser = new RTDPAvailabilityRQParser();
                        RTDPAvailabilityRQ rtdpRQ = rtdpRQparser.RTDPAvailabilityRQProcessLines(sectionLines);
                        if (cpr.rtdpAvailabilityRQ == null) { cpr.rtdpAvailabilityRQ = new ArrayList<>(); }
                        cpr.rtdpAvailabilityRQ.add(rtdpRQ);
                        break;
                    case RTDPAvailabilityRS:
                        break;
                    default:
                        break;
                }
            }
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage() + " - module: " + moduleType.toString());
            e.printStackTrace();
        }
    }
    /*  Enumeraciones de referencia para procesar
        los archivos y detectar los bloques
    */
    public static enum ModuleType {
        CcitinRQ, CcitinRS,
        OTA_AirAvailRQ, OTA_AirAvailRS,
        InventoryRQ, InventoryRS,
        RTDPAvailabilityRQ, RTDPAvailabilityRS
    }
}
