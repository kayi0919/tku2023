package org.tku.database.custom.Impl;

import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.tku.database.custom.CartRepositoryCustom;
import org.tku.database.custom.PendingRepositoryCustom;
import org.tku.database.entity.Cart;
import org.tku.database.entity.Orders;
import org.tku.database.repository.CartRepository;
import org.tku.database.repository.PendingRepository;

import java.util.ArrayList;
import java.util.List;

public class PendingRepositoryCustomImpl implements PendingRepositoryCustom {

    private final PendingRepository pendingRepository;

    public PendingRepositoryCustomImpl(@Lazy PendingRepository pendingRepository){
        this.pendingRepository = pendingRepository;
    }

    @Override
    public List<Orders> findCartsBySelective(Orders orders) {
        return pendingRepository.findAll((root, query, cb) -> {
            query.distinct(true);
            query.orderBy(cb.asc(root.get("orderId")));

            final List<Predicate> predicates = new ArrayList<>();
            if (orders.getUsername() != null) {
                predicates.add(cb.equal(root.get("username"), orders.getUsername()));
            }

            if (StringUtils.isNotBlank(orders.getOrders_product_name())) {
                predicates.add(cb.like(root.get("product_name"), "%" + orders.getOrders_product_name() + "%"));
            }

            if (orders.getOrders_product_price() != null) {
                predicates.add(cb.equal(root.get("product_price"), orders.getOrders_product_price()));
            }

            if (orders.getOrders_amount() != null) {
                predicates.add(cb.equal(root.get("amount"), orders.getOrders_amount()));
            }

            if (orders.getOrders_total() != null) {
                predicates.add(cb.equal(root.get("total"), orders.getOrders_total()));
            }

            if (orders.getOperation() != null) {
                predicates.add(cb.equal(root.get("operation"), orders.getOperation()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
