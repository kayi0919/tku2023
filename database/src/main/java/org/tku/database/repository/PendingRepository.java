package org.tku.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.tku.database.custom.CartRepositoryCustom;
import org.tku.database.custom.PendingRepositoryCustom;
import org.tku.database.entity.Cart;
import org.tku.database.entity.Orders;

public interface PendingRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders>, PendingRepositoryCustom {
}
