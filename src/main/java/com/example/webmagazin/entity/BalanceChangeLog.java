package com.example.webmagazin.entity;

import com.example.webmagazin.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceChangeLog extends AbsEntity {
   @OneToOne
   private User user;
   private Double amount;
}
