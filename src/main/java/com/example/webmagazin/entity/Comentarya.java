package com.example.webmagazin.entity;

import com.example.webmagazin.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comentarya  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    private User user;

    private String text;

    private UUID productId;
}
