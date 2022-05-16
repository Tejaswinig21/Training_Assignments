package com.modals;

import javax.persistence.*;

@Entity
@Table(name = "`OrderedItem`")
public class OrderedItem {

    @Id
    @Column(name = "id")
    private int orderId;
    @Column(name = "dateOfOrder")
    private String date;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Column(name = "phoneNumber")
    private String contact;
    @Column(name = "item")
    private String itemName;
    @Column(name = "price")
    private int price;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "quantity")
    private int quantity;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Column(name = "total")
    private int total;

    @ManyToOne(optional = false)
    @JoinColumn(name = "itemId",referencedColumnName = "itemId")
    private Item item;

    @ManyToOne(optional = false)
    @JoinColumn(name = "invoiceId",referencedColumnName = "id")
    private Invoice invoice;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "OrderedItem{" +
                "orderId=" + orderId +
                ", date='" + date + '\'' +
                ", contact='" + contact + '\'' +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", total=" + total +
                ", item=" + item +
                ", invoice=" + invoice +
                '}';
    }
}
