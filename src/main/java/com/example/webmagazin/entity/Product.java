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
    private Double newPrice;
    private Double oldPrice;
    private Long productType;
    private String productTypeName;
    @ManyToMany
    private List<Attachment> attachments;
    private Integer count;

    private String vendor;
    private Long interests;



}
