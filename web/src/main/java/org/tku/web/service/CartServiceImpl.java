package org.tku.web.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tku.database.entity.Cart;
import org.tku.database.repository.CartRepository;

import java.util.List;

@Service
@Log4j2
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAllPurchaseRecords() {
        return cartRepository.findAll();
    }
}
