package com.services;

import com.modals.Invoice;

import java.util.List;

public interface InvoiceService {
    List<Invoice> getInvoices();

    List<Invoice> getRevenueByYear();
    List<Invoice> getRevenueByMonth();

    List<Invoice> getTodaysData();
    int addInvoice(Invoice invoice);
}
