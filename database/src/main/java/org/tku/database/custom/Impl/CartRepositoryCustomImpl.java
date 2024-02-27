package org.tku.database.custom.Impl;

import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.tku.database.custom.CartRepositoryCustom;
import org.tku.database.entity.Cart;
import org.tku.database.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

public class CartRepositoryCustomImpl implements CartRepositoryCustom {

    private final CartRepository cartRepository;

    public CartRepositoryCustomImpl(@Lazy CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> findCartsBySelective(Cart cart) {
        return cartRepository.findAll((root, query, cb) -> {
            query.distinct(true);
            query.orderBy(cb.asc(root.get("record_seq")));

            final List<Predicate> predicates = new ArrayList<>();
            if (cart.getRecord_seq() != null) {
                predicates.add(cb.equal(root.get("record_seq"), cart.getRecord_seq()));
            }

            if (StringUtils.isNotBlank(cart.getProduct_name())) {
                predicates.add(cb.like(root.get("product_name"), "%" + cart.getProduct_name() + "%"));
            }

            if (cart.getProduct_price() != null) {
                predicates.add(cb.equal(root.get("product_price"), cart.getProduct_price()));
            }

            if (cart.getAmount() != null) {
                predicates.add(cb.equal(root.get("amount"), cart.getAmount()));
            }
            if (cart.getTotal() != null) {
                predicates.add(cb.equal(root.get("total"), cart.getTotal()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
