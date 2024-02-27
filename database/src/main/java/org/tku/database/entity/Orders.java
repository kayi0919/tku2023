package org.tku.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "orders", schema = "public", catalog = "tku2023")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_Id")
    private Integer orderId;

    @Column(name = "username")
    private String username;

    @Column(name = "product_name")
    private String orders_product_name;

    @Column(name = "product_price")
    private Integer orders_product_price;

    @Column(name = "amount")
    private Integer orders_amount;

    @Column(name = "total")
    private Integer orders_total;

    @Column(name = "operation")
    private String operation;

    @Column(name = "received_time")
    private Timestamp received_time;

    @PrePersist
    protected void onCreate() {
        received_time = new Timestamp(System.currentTimeMillis());
    }
}
