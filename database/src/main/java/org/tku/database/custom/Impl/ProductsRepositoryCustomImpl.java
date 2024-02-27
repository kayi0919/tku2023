package org.tku.database.custom.Impl;

import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.tku.database.custom.PendingRepositoryCustom;
import org.tku.database.custom.ProductsRepositoryCustom;
import org.tku.database.entity.Orders;
import org.tku.database.entity.Products;
import org.tku.database.repository.PendingRepository;
import org.tku.database.repository.ProductsRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductsRepositoryCustomImpl implements ProductsRepositoryCustom {

    private final ProductsRepository productsRepository;

    public ProductsRepositoryCustomImpl(@Lazy ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Products> findProductsBySelective(Products products) {
        return productsRepository.findAll((root, query, cb) -> {
            query.distinct(true);
            query.orderBy(cb.asc(root.get("products_no")));

            final List<Predicate> predicates = new ArrayList<>();
            if (products.getProducts_name() != null) {
                predicates.add(cb.equal(root.get("product_name"), "%" + products.getProducts_name() + "%"));
            }

            if (products.getProducts_price() != null) {
                predicates.add(cb.equal(root.get("product_price"), products.getProducts_price()));
            }

            if (products.getProducts_amount() != null) {
                predicates.add(cb.equal(root.get("product_amount"), products.getProducts_amount()));
            }

            if (products.getProducts_type() != null) {
                predicates.add(cb.equal(root.get("product_type"), products.getProducts_type()));
            }

            if (products.getProducts_byte() != null) {
                predicates.add(cb.isNotNull(root.get("products_byte"))); // 假设 products_byte 是存储图像的字段名
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
