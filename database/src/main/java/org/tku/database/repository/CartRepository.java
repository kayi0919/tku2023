package org.tku.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.tku.database.custom.CartRepositoryCustom;
import org.tku.database.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart>, CartRepositoryCustom {
}
