/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.auxiliar;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fazuniga
 */
public final class Constants {

    public static String[] LATAM_CARRIERS = { "LA", "XL", "4M" };
    public static int FLTNUM_DIGITS = 5;
    
    public static Boolean testing = true;
    
    private static int BID_PRICE_ERROR_VALUE = -10;
    
    private static String FILE_FORMAT = ".xml";
    private static String INPUT_FILES_FOLDER = "inputFiles";

    public static int getBID_PRICE_ERROR_VALUE() {
        return BID_PRICE_ERROR_VALUE;
    }
    
    public static String getFileFormat() {
        return FILE_FORMAT;
    }
    
    public static String getInputFilesFolder() {
        return INPUT_FILES_FOLDER;
    }

    private static Charset PROJECT_ENCODING = StandardCharsets.UTF_8;
    public static Charset getProjectEncoding() {
        return PROJECT_ENCODING;
    }
    
    public static File[] getInputFiles(String inputFilesFolder, final String fileFormat) {
        File dir = new File(inputFilesFolder);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(fileFormat);
            }
        });
        
        return files;
    }
}
