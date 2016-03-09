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
public class CcitinRSDirection {
    
    private int DirectionID;
    private List<CcitinRSDirection_Option> Options;

    public CcitinRSDirection() {
    }

    public int getDirectionID() {
        return DirectionID;
    }

    public void setDirectionID(int DirectionID) {
        this.DirectionID = DirectionID;
    }

    public List<CcitinRSDirection_Option> getOptions() {
        return Options;
    }

    public void setOptions(List<CcitinRSDirection_Option> Options) {
        this.Options = Options;
    }
}
