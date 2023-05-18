package sporteventmanagement.entity;

import java.util.Date;

public class TicketDetails {

    private int ticketID;
    private String customerName;
    private float ticketPrice;
    private Match matchID;
    private Field fieldID;
    private Date lastUpdate;

    public TicketDetails() {
    }

    public TicketDetails(int ticketID, String customerName, float ticketPrice, Match matchID, Field fieldID, Date lastUpdate) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.ticketPrice = ticketPrice;
        this.matchID = matchID;
        this.fieldID = fieldID;
        this.lastUpdate = lastUpdate;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Match getMatchID() {
        return matchID;
    }

    public void setMatchID(Match matchID) {
        this.matchID = matchID;
    }

    public Field getFieldID() {
        return fieldID;
    }

    public void setFieldID(Field fieldID) {
        this.fieldID = fieldID;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
