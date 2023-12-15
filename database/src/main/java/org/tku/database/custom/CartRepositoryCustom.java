package org.tku.database.custom;

import org.tku.database.entity.Cart;

import java.util.List;

public interface CartRepositoryCustom {
    List<Cart> findCartsBySelective(Cart cart);
}
