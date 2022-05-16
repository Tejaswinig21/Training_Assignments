package com.modals;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`invoice`")
public class Invoice {
    @Id
    @Column(name = "id")
    private int invoiceId;
    @Column(name = "dateOfOrder")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "phoneNumber")
    private String contact;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "Amount")
    private int totalAmount;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "invoice")
    private List<OrderedItem> orderedItems=new ArrayList<>();


    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }




}
