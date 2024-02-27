package org.tku.database.custom;



import org.tku.database.entity.Orders;

import java.util.List;

public interface PendingRepositoryCustom {
    List<Orders> findCartsBySelective(Orders orders);
}
