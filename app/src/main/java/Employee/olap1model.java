package Employee;

public class olap1model {
    String name;
    String phone;
    String count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public olap1model(String name, String phone, String count) {
        this.name = name;
        this.phone = phone;
        this.count = count;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
