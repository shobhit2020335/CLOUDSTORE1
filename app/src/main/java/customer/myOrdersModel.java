package customer;

public class myOrdersModel {
    String oid,ocost,ostatus,odate,oquantity,pid,pname,opaymode;

    public myOrdersModel() {
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOcost() {
        return ocost;
    }

    public void setOcost(String ocost) {
        this.ocost = ocost;
    }

    public String getOstatus() {
        return ostatus;
    }

    public void setOstatus(String ostatus) {
        this.ostatus = ostatus;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public String getOquantity() {
        return oquantity;
    }

    public void setOquantity(String oquantity) {
        this.oquantity = oquantity;
    }

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

    public String getOpaymode() {
        return opaymode;
    }

    public void setOpaymode(String opaymode) {
        this.opaymode = opaymode;
    }

    public myOrdersModel(String oid, String ocost, String ostatus, String odate, String oquantity, String pid, String pname, String opaymode) {
        this.oid = oid;
        this.ocost = ocost;
        this.ostatus = ostatus;
        this.odate = odate;
        this.oquantity = oquantity;
        this.pid = pid;
        this.pname = pname;
        this.opaymode = opaymode;
    }
}
