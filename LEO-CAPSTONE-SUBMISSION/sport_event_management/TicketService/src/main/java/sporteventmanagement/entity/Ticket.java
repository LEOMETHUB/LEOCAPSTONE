package sporteventmanagement.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "ticket")
@Table(name = "tickets", schema = "ticket_schema")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int ticketID;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "ticket_price")
    private float ticketPrice;
    @Column(name = "match_id")
    private int matchID;
    @Column(name ="field_id")
    private int fieldID;
    @Column(name = "last_update")
    private Date lastUpdate;

    public Ticket() {
    }



    public Ticket(int ticketID, String customerName, float ticketPrice, int matchID, int fieldID, Date lastUpdate) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.ticketPrice = ticketPrice;
        this.matchID = matchID;
        this.fieldID = fieldID;
        this.lastUpdate = lastUpdate;
    }

    public Ticket(String customerName, float ticketPrice, int matchID, int fieldID, Date lastUpdate) {
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

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getFieldID() {
        return fieldID;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
