package Product;

import java.io.Serializable;

public class ProductCount  implements Serializable {
    String PName;
    String PID;
    String PQuantity;

    public ProductCount() {
    }

    public ProductCount(String PName, String PID, String PQuantity) {
        this.PName = PName;
        this.PID = PID;
        this.PQuantity = PQuantity;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getPQuantity() {
        return PQuantity;
    }

    public void setPQuantity(String PQuantity) {
        this.PQuantity = PQuantity;
    }
}
