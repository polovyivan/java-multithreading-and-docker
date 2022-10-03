package com.polovyi.ivan.tutorials.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PurchaseTransactionEntity {

    private String id;

    private String customerId;

    private String paymentType;

    private BigDecimal amount;

    private LocalDate createdAt;
}
