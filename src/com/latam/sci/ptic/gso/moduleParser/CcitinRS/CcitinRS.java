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
    
    private List<CcitinRSDirection> Directions;
    
    public CcitinRS() { }

    public List<CcitinRSDirection> getDirections() {
        return Directions;
    }

    public void setDirections(List<CcitinRSDirection> Directions) {
        this.Directions = Directions;
    }
}