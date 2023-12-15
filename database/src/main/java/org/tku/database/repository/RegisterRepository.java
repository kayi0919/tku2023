package org.tku.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.tku.database.custom.CartRepositoryCustom;
import org.tku.database.custom.RegisterRepositoryCustom;
import org.tku.database.entity.Cart;
import org.tku.database.entity.User;

public interface RegisterRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, RegisterRepositoryCustom {
}
