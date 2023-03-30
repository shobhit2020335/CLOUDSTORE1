package Employee;

public class olap2model {
    String pid,pname,sales,quantity;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public olap2model() {
    }

    public olap2model(String pid, String pname, String sales, String quantity) {
        this.pid = pid;
        this.pname = pname;
        this.sales = sales;
        this.quantity = quantity;
    }
}
