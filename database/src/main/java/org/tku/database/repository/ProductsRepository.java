package org.tku.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.tku.database.custom.ProductsRepositoryCustom;
import org.tku.database.entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Long>, JpaSpecificationExecutor<Products>, ProductsRepositoryCustom {
}
