package com.stefanyshyn.ecommerce.dao;

import com.stefanyshyn.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product,Long> {
}
