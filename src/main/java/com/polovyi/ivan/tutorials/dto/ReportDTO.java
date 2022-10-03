package com.polovyi.ivan.tutorials.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ReportDTO {

    private String id;

    private String fullName;

    private BigDecimal totalAmountSpend;

}
