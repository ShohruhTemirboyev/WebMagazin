package com.example.webmagazin.entity;

import com.example.webmagazin.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsEntity {
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    private Long productType;
    @OneToMany
    private List<Attachment> attachments;
    private Integer count;
    private boolean bestseller;
    private boolean superPrice;
    private boolean newProduct;
    private String vendor;



}
