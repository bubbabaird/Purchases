package com.theironyard.charlotte;

import javax.persistence.*;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue
    int purchaseId;

//    Don't store the customer_id in the purchase class
//    because the join system will create that column for us

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    String creditCard;

    @Column(nullable = false)
    int cvv;

    @Column(nullable = false)
    String category;

    @ManyToOne
    Customer customer;

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCredit_card() {
        return creditCard;
    }

    public void setCredit_card(String credit_card) {
        this.creditCard = credit_card;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
//    @Column(nullable = false, unique = true)
//    int customer_id;

    public Purchase() {
    }

    public Purchase(Customer customer, String date, String credit_card, int cvv, String category) {
        this.customer = customer;
        this.date = date;
        this.creditCard = credit_card;
        this.cvv = cvv;
        this.category = category;
    }
}
