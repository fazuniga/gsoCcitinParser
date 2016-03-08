/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.CcitinRQ;

import java.util.List;

/**
 *
 * @author 641515
 */
public class CcitinRQ {
    
    private List<CcitinRQDirection> Directions;
    
    public CcitinRQ() {}

    public List<CcitinRQDirection> getDirections() {
        return Directions;
    }

    public void setDirections(List<CcitinRQDirection> Directions) {
        this.Directions = Directions;
    }
}
