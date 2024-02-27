package org.tku.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "purchase_record", schema = "public", catalog = "tku2023")
//@Table(name = "purchase_record")
public class Cart {
    @Id
    @Column(name = "record_seq")
    private Long record_seq;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "product_price")
    private Long product_price;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "total")
    private Long total;

    @Transient
    private String action;
}
