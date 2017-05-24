package com.theironyard.charlotte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;

@Controller
public class PurchasesController {
    // you can add Autowired to your fields, it will cause spring to populate this field when it
    // creates this controller.  This is a process called dependency injection.
    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchasesRepository purchases;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String credit_card, Integer cvv, String category, String date) {
//        String currentCustomer = (String) session.getAttribute("currentCustomer");
//        Customer customer = customers.findByName(currentCustomer);
//        if (customer != null) {
//            model.addAttribute("customer", customer);
//        }
//
        List<Purchase> purchaseList;
        // if there is a category specified...
        if (category != null) {
            // set purchase list to our find by category call
            purchaseList = purchases.findByCategory(category);
        } else if (credit_card != null) {
            purchaseList = purchases.findByCreditCard(credit_card);
        } else if (cvv != null) {
            purchaseList = purchases.findByCvv(cvv);
        } else if (date != null) {
            purchaseList = purchases.findByDate(date);
        }
        else {
            // if there is no category, just return all purchases
            purchaseList = (List)purchases.findAll();
        }
        model.addAttribute("purchases", purchaseList);
        return "home";
    }

//    }

    @PostConstruct
    // for every line in the file
    // convert that line into a customer
    // save customer in the repository
    public void Init() throws IOException {
        if (customers.count() == 0) {
            BufferedReader br = new BufferedReader(new FileReader(new File("customers.csv")));

            // reads in the first line from the file
            br.readLine();

            // stores the current line we're talking about
            String eachLine;
            while ((eachLine = br.readLine()) != null) {
                // take that data and convert it to a customer object, name and email
                // pass the first and second column into your customer constructor
                String[] columns = eachLine.split("\\,");
                Customer customer = new Customer(columns[0], columns[1]);
                customers.save(customer);

                // columns[0] is the first thing in the eachLine (the name)
                // columns[1] is the second thing in eachLine (the email address)

                // we need to take those two pieces of data and build a customer object
                // save the customer object into our customers repository
            }
        }
        if (purchases.count() == 0) {
            BufferedReader brPurchases = new BufferedReader(new FileReader(new File("purchases.csv")));

            // reads in the first line from the file
            brPurchases.readLine();

            // stores the current line we're talking about
            String eachLineFP;
            while ((eachLineFP = brPurchases.readLine()) != null) {
                // take that data and convert it to a customer object, name and email
                // pass the first and second column into your customer constructor
                String[] columns = eachLineFP.split("\\,");

                int id = Integer.valueOf(columns[0]);
                Customer customer = customers.findOne(id);

                Purchase purchase = new Purchase(customer, columns[1], (columns[2]), Integer.valueOf(columns[3]), columns[4]);
                purchases.save(purchase);
                // customer_id, date, credit_card, cvv, category

                // columns[0] is the first thing in the eachLine (the name)
                // columns[1] is the second thing in eachLine (the email address)

                // we need to take those two pieces of data and build a customer object
                // save the customer object into our customers repository
            }
        }
    }
}