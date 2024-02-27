package org.tku.web.service;

import org.tku.database.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllPurchaseRecords();
}
