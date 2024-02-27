package org.tku.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "products", schema = "public", catalog = "tku2023")
public class Products {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "products_no")
    private Integer products_no;

    @Column(name = "products_name")
    private String products_name;

    @Column(name = "products_price")
    private Long products_price;

    @Column(name = "products_amount")
    private Long products_amount;

    @Column(name = "products_type")
    private String products_type;

    
    @Column(name = "products_byte")
    private byte[] products_byte;
}
