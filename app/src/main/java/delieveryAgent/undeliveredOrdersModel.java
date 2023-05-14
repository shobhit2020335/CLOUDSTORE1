package delieveryAgent;

public class undeliveredOrdersModel {
    String oid,ocost,ostatus,cname,cphone,caddress,opaymode;

    public undeliveredOrdersModel() {
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

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getOpaymode() {
        return opaymode;
    }

    public void setOpaymode(String opaymode) {
        this.opaymode = opaymode;
    }

    public undeliveredOrdersModel(String oid, String ocost, String ostatus, String opaymode, String cname, String cphone, String caddress) {
        this.oid = oid;
        this.ocost = ocost;
        this.ostatus = ostatus;
        this.opaymode=opaymode;
        this.cname=cname;
        this.cphone = cphone;
        this.caddress = caddress;
    }
}
