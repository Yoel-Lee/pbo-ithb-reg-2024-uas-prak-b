package Model;

import java.sql.Date;

public class DeliveryDetails {
    private int id;
    private int transaction_id;
    private Status status;
    private String current_position;
    private String evidence;
    private Date date;
    private String updated_by;
    public DeliveryDetails(int id, int transaction_id, Status status, String current_position, String evidence,
            Date date, String updated_by) {
        this.id = id;
        this.transaction_id = transaction_id;
        this.status = status;
        this.current_position = current_position;
        this.evidence = evidence;
        this.date = date;
        this.updated_by = updated_by;
    }
}
