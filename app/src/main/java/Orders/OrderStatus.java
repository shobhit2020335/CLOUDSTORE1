package Orders;

import java.io.Serializable;

public class OrderStatus implements Serializable {
    String OID;
    String OStatus;
    String OCost;
    String ODate;

    public OrderStatus() {
    }

    public OrderStatus(String OID, String OStatus, String OCost, String ODate) {
        this.OID = OID;
        this.OStatus = OStatus;
        this.OCost = OCost;
        this.ODate = ODate;
    }

    public String getOID() {
        return OID;
    }

    public void setOID(String OID) {
        this.OID = OID;
    }

    public String getOStatus() {
        return OStatus;
    }

    public void setOStatus(String OStatus) {
        this.OStatus = OStatus;
    }

    public String getOCost() {
        return OCost;
    }

    public void setOCost(String OCost) {
        this.OCost = OCost;
    }

    public String getODate() {
        return ODate;
    }

    public void setODate(String ODate) {
        this.ODate = ODate;
    }
}
