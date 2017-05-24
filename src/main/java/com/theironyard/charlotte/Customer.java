package com.theironyard.charlotte;

import javax.persistence.*;

/**
 * Created by BUBBABAIRD on 5/10/17.
 */
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue
    int customerId;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String email;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Customer() {

    }

    public Customer(String name, String email) {

        this.name = name;
        this.email = email;
    }
}
