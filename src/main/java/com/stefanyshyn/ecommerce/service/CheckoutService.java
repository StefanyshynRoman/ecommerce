package com.stefanyshyn.ecommerce.service;

import com.stefanyshyn.ecommerce.dto.PaymentInfo;
import com.stefanyshyn.ecommerce.dto.Purchase;
import com.stefanyshyn.ecommerce.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
