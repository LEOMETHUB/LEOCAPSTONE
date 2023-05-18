package sporteventmanagement.pojo;


public class TicketPojo {
    private  String customerName;
    private  float ticketPrice;
    private int matchID;

    public String getCustomerNamePojo() {
        return customerName;
    }

    public void setCustomerNamePojo(String customerName) {
        this.customerName = customerName;
    }

    public float getTicketPricePojo() {
        return ticketPrice;
    }

    public void setTicketPricePojo(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getMatchIDPojo() {
        return matchID;
    }

    public void setMatchIDPojo(int matchID) {
        this.matchID = matchID;
    }
}
