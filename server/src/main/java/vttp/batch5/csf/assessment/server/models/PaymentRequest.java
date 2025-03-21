package vttp.batch5.csf.assessment.server.models;

public class PaymentRequest {

    private String order_id;  // Your generated 8-char order ID
    private String payer;     // e.g. "fred" from the validated order
    private String payee;     // the "official name" you pass in
    private float payment;
    
    public PaymentRequest() {
    }
    public String getOrder_id() {
        return order_id;
    }
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    public String getPayer() {
        return payer;
    }
    public void setPayer(String payer) {
        this.payer = payer;
    }
    public String getPayee() {
        return payee;
    }
    public void setPayee(String payee) {
        this.payee = payee;
    }
    public float getPayment() {
        return payment;
    }
    public void setPayment(float payment) {
        this.payment = payment;
    }

    
    
}
