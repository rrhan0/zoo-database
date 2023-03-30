package model;

public class Raw_Food_Order {
    private final String o_id;
    private final String contents;
    private final Integer weight;
    private final String date_received;

    public Raw_Food_Order(String o_id, String contents, Integer weight, String date_recieved) {
        this.o_id = o_id;
        this.contents = contents;
        this.weight = weight;
        this.date_received = date_recieved;
    }

    public String getO_id() {
        return o_id;
    }

    public String getContents() {
        return contents;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getDate_received() {
        return date_received;
    }
}
