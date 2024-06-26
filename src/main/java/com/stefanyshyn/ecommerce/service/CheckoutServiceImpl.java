package com.stefanyshyn.ecommerce.service;

import com.stefanyshyn.ecommerce.dao.CustomerRepository;
import com.stefanyshyn.ecommerce.dto.Purchase;
import com.stefanyshyn.ecommerce.dto.PurchaseResponse;
import com.stefanyshyn.ecommerce.entity.Customer;
import com.stefanyshyn.ecommerce.entity.Order;
import com.stefanyshyn.ecommerce.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Order order = purchase.getOrder();
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        Customer customer = purchase.getCustomer();

        customerRepository.save(customer);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        //generate a random UUID
        return UUID.randomUUID().toString();
    }
}
