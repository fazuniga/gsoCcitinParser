/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.main;

import com.latam.sci.ptic.gso.auxiliar.Constants;
import java.io.File;
import java.util.ArrayList;
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
        }
    }
}