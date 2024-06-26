package com.stefanyshyn.ecommerce.service;

import com.stefanyshyn.ecommerce.dto.Purchase;
import com.stefanyshyn.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
