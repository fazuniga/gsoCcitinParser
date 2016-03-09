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
public class CcitinRSDirection_Option {
    
    private int EFT, OptionID;
    private List<CcitinRSDirection_OptionFlight> Flights;

    public CcitinRSDirection_Option() {
    }

    public int getEFT() {
        return EFT;
    }

    public void setEFT(int EFT) {
        this.EFT = EFT;
    }

    public int getOptionID() {
        return OptionID;
    }

    public void setOptionID(int OptionID) {
        this.OptionID = OptionID;
    }

    public List<CcitinRSDirection_OptionFlight> getFlights() {
        return Flights;
    }

    public void setFlights(List<CcitinRSDirection_OptionFlight> Flights) {
        this.Flights = Flights;
    }
}
