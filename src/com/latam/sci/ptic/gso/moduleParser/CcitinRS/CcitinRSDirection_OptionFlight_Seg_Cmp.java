/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.CcitinRS;

import java.util.List;

/**
 *
 * @author 641515
 */
public class CcitinRSDirection_OptionFlight_Seg_Cmp {
    private String CmpCode;
    private int groupBooked;
    
    private List<CcitinRSDirection_OptionFlight_Seg_Cmp_Class> Classes;

    public CcitinRSDirection_OptionFlight_Seg_Cmp() {
    }
    
    public List<CcitinRSDirection_OptionFlight_Seg_Cmp_Class> getClasses() {
        return Classes;
    }

    public void setClasses(List<CcitinRSDirection_OptionFlight_Seg_Cmp_Class> Classes) {
        this.Classes = Classes;
    }

    public String getCmpCode() {
        return CmpCode;
    }

    public void setCmpCode(String CmpCode) {
        this.CmpCode = CmpCode;
    }

    public int getGroupBooked() {
        return groupBooked;
    }

    public void setGroupBooked(int groupBooked) {
        this.groupBooked = groupBooked;
    }
}
