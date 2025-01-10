package Model;

import java.sql.Date;

public class Transaction {
    private int id;
    private int customer_id;
    private String delivery_type;
    private int expected_weight;
    private int total_cost;
    private Date created_at;
    private String receipt_name;
    private String receipt_address;
    private String receipt_phone;


    
    public Transaction(int id, int customer_id, String delivery_type, int expected_weight, int total_cost,
            Date created_at, String receipt_name, String receipt_address, String receipt_phone) {
        this.id = id;
        this.customer_id = customer_id;
        this.delivery_type = delivery_type;
        this.expected_weight = expected_weight;
        this.total_cost = total_cost;
        this.created_at = created_at;
        this.receipt_name = receipt_name;
        this.receipt_address = receipt_address;
        this.receipt_phone = receipt_phone;
    }
}
