package com.coursework.ecomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Setter
@Getter
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;

    @Column
    private String productName;

    @Column
    private int productPrice;

    @Column
    private int productUnit;

    private String productDescription;
    private String image;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category = new Category();

    @ManyToMany(mappedBy = "productList",fetch = FetchType.EAGER)
    private List<User> userList;

    public Product() {
        this.productName = "";
        this.productDescription = "";
    }


}
