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
public class CcitinRS {
    private List<CcitinRSElement> ccitinRS = null;

    public List<CcitinRSElement> getCcitinRS() {
        return ccitinRS;
    }

    public void setCcitinRS(List<CcitinRSElement> ccitinRS) {
        this.ccitinRS = ccitinRS;
    }
    
    public CcitinRS() { }
}