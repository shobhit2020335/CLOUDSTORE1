package customer;

public class myQueriesModel {
    String qid,qtext,qstatus;

    public myQueriesModel() {
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

    public myQueriesModel(String qid, String qtext, String qstatus) {
        this.qid = qid;
        this.qtext = qtext;
        this.qstatus = qstatus;
    }
}
