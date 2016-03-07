/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.latam.sci.ptic.gso.moduleParser.OTA_AirAvailRS;

/**
 *
 * @author 641515
 */
public class OTA_AirAvailRSFlightSegment {
    public static String OTA_AirAvailRSFlightSegment_start = "^\\s*<FlightSegment ArrivalDateTime=\"(?<ArrivalDateTime>[0-9:T-]{11})\"[ ]*(ConnectionInd=\"(?<ConnectionInd>[Y|N]*?)\")?[ ]*(DOT_Ind=\"(?<DOTInd>[Y|N]*?)\")? DepartureDateTime=\"(?<DepartureDateTime>[0-9:T-]{11})\" FlightNumber=\"(?<FlightNumber>\\d+)\" RPH=\"(?<FlightRPH>\\d+)\".*$";
    public static String OTA_AirAvailRSFlightSegment_end = "^\\s*</FlightSegment>";
    
    public static String OTA_AirAvailRSFlightSegment_Origin = "^\\s*<OriginLocation LocationCode=\"(?<OriginLocation>[A-Z]{3})\".*$";
    public static String OTA_AirAvailRSFlightSegment_Destination = "^\\s*<DestinationLocation LocationCode=\"(?<DestinationLocation>[A-Z]{3})\".*$";
    public static String OTA_AirAvailRSFlightSegment_MarketingAirline = "^\\s*<MarketingAirline Code=\"(?<MarketingAirlineCode>[A-Z0-9]{2})\" FlightNumber=\"(?<MarketingFlightNumber>\\d+).*$";

    private String DepartureDate, DepartureTime;
    private String ArrivalDate, ArrivalTime;
    private String ConnectionInd = "", DOTInd = "";
    private String FlightNumber, FlightRPH;
    
    private String OriginLocation, DestinationLocation;
    private String MarketingAirlineCode, MarketingFlightNumber;
    
    public OTA_AirAvailRSFlightSegment() {}    
    
    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String DepartureDate) {
        this.DepartureDate = DepartureDate;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(String DepartureTime) {
        this.DepartureTime = DepartureTime;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String ArrivalDate) {
        this.ArrivalDate = ArrivalDate;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String ArrivalTime) {
        this.ArrivalTime = ArrivalTime;
    }
    
    public String getConnectionInd() {
        return ConnectionInd;
    }

    public void setConnectionInd(String ConnectionInd) {
        this.ConnectionInd = ConnectionInd;
    }

    public String getDOTInd() {
        return DOTInd;
    }

    public void setDOTInd(String DOTInd) {
        this.DOTInd = DOTInd;
    }

    public String getFlightNumber() {
        return FlightNumber;
    }

    public void setFlightNumber(String FlightNumber) {
        this.FlightNumber = FlightNumber;
    }

    public String getFlightRPH() {
        return FlightRPH;
    }

    public void setFlightRPH(String FlightRPH) {
        this.FlightRPH = FlightRPH;
    }

    public String getOriginLocation() {
        return OriginLocation;
    }

    public void setOriginLocation(String OriginLocation) {
        this.OriginLocation = OriginLocation;
    }

    public String getDestinationLocation() {
        return DestinationLocation;
    }

    public void setDestinationLocation(String DestinationLocation) {
        this.DestinationLocation = DestinationLocation;
    }

    public String getMarketingAirlineCode() {
        return MarketingAirlineCode;
    }

    public void setMarketingAirlineCode(String MarketingAirlineCode) {
        this.MarketingAirlineCode = MarketingAirlineCode;
    }

    public String getMarketingFlightNumber() {
        return MarketingFlightNumber;
    }

    public void setMarketingFlightNumber(String MarketingFlightNumber) {
        this.MarketingFlightNumber = MarketingFlightNumber;
    }
}
