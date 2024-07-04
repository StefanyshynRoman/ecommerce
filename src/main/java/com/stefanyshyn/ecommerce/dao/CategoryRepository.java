package com.stefanyshyn.ecommerce.dao;

import com.stefanyshyn.ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {
}
