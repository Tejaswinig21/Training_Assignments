package com.controllers;

import com.modals.Invoice;
import com.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class InvoiceController {
    @Autowired
    @Qualifier("invoice")
    private InvoiceService invoiceService;

    @GetMapping("/getInvoices")
    public List<Invoice> getInvoices(){
        return invoiceService.getInvoices();
    }

    @PostMapping("/addInvoices")
    public int addInvoice(@RequestBody Invoice invoice){
        return invoiceService.addInvoice(invoice);
    }

    @GetMapping("/getRevenueByYear")
    public List<Invoice> getRevenueByYear(){
        return invoiceService.getRevenueByYear();
    }

    @GetMapping("/getRevenueByMonth")
    public List<Invoice> getRevenueByMonth(){
        return invoiceService.getRevenueByMonth();
    }

    @GetMapping("/getTodaysData")
    public List<Invoice> getTodaysData(){
        return invoiceService.getTodaysData();
    }
}
