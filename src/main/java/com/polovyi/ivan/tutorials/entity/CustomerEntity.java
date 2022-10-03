package com.polovyi.ivan.tutorials.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class CustomerEntity {

    private String id;

    private String fullName;

    private String phoneNumber;

    private String address;

    private LocalDate createdAt;

    private Set<PurchaseTransactionEntity> purchaseTransactions;

}