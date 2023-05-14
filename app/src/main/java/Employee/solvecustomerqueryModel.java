package Employee;

public class solvecustomerqueryModel {
    String cphone,qid,qtext,qstatus;

    public solvecustomerqueryModel() {
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getQtext() {
        return qtext;
    }

    public void setQtext(String qtext) {
        this.qtext = qtext;
    }

    public String getQstatus() {
        return qstatus;
    }

    public void setQstatus(String qstatus) {
        this.qstatus = qstatus;
    }

    public solvecustomerqueryModel(String cphone, String qid, String qtext, String qstatus) {
        this.cphone = cphone;
        this.qid = qid;
        this.qtext = qtext;
        this.qstatus = qstatus;
    }
}
