/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author benha
 */
public class PaymentService {

    private static final String TEST_STRIPE_SECRET_KEY = "sk_test_V8FOf7gAopKqKJZcsnPYHyK4009oxzxVgV";

    public PaymentService() {
        Stripe.apiKey = TEST_STRIPE_SECRET_KEY;
    }

    public String createCustomer(String city, String adresse, String name, String email, String phonenumber) throws StripeException {

        Map<String, Object> address = new HashMap<>();
        address.put("line1", adresse);
        address.put("city", city);
        address.put("country", "TUNISIA");
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("name", name);
        params.put("phone", phonenumber);
        params.put("address", address);

        Customer customer = Customer.create(params);

        System.out.println("customer id :*****" + customer.getId() + "********");
        return customer.getId();

    }

    public void AttachCardToCustomer(String cardnumber, int month, int year, String cvc, String customer_id) throws StripeException {
        Customer customer = Customer.retrieve(customer_id);

        Map<String, Object> Cardparams = new HashMap<>();
        Cardparams.put("number", cardnumber);
        Cardparams.put("exp_month", month);
        Cardparams.put("exp_year", year);
        Cardparams.put("cvc", cvc);

        Map<String, Object> params = new HashMap<>();
        params.put("card", Cardparams);

        Token token = Token.create(params);

        Map<String, Object> source = new HashMap<>();

        source.put("source", token.getId());

    //    Card card = (Card) customer.getSources().create(source);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println(gson.toJson(customer));

    }

    public void chargeCreditCard(String total_a_payer, String customer_id) throws StripeException {

        long x = Long.parseLong(total_a_payer);

        System.out.println("****************** total long " + x + "***********************");
        long amount = x * 100;
        System.out.println("******************* amount" + amount + "******************");
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", "usd");
        params.put("customer", customer_id);

        Charge charge = Charge.create(params);

        System.out.println("***************************************");
        System.out.println(charge.toString());
        System.out.println("***************************************");
    }

}
