package org.tku.database.custom;



import org.tku.database.entity.Orders;
import org.tku.database.entity.Products;

import java.util.List;

public interface ProductsRepositoryCustom {
    List<Products> findProductsBySelective(Products products);
}
