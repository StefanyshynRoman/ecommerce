package com.stefanyshyn.ecommerce.dto;

import com.stefanyshyn.ecommerce.entity.Address;
import com.stefanyshyn.ecommerce.entity.Customer;
import com.stefanyshyn.ecommerce.entity.Order;
import com.stefanyshyn.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
