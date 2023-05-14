package customer;

public class cartModel {
    String pid,pprice,pname,pquantity;

    public cartModel() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPquantity() {
        return pquantity;
    }

    public void setPquantity(String pquantity) {
        this.pquantity = pquantity;
    }

    public cartModel(String pid, String pprice, String pname, String pquantity) {
        this.pid = pid;
        this.pprice = pprice;
        this.pname = pname;
        this.pquantity=pquantity;
    }
}
