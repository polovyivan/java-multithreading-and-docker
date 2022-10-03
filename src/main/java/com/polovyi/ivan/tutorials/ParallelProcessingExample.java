package com.polovyi.ivan.tutorials;

import com.polovyi.ivan.tutorials.configuration.FakeDataBase;
import com.polovyi.ivan.tutorials.dto.ReportDTO;
import com.polovyi.ivan.tutorials.entity.CustomerEntity;
import com.polovyi.ivan.tutorials.entity.PurchaseTransactionEntity;
import com.polovyi.ivan.tutorials.utils.SleepUtils;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ParallelProcessingExample {

    public static void main(String[] args) {

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        LocalDateTime startTime = LocalDateTime.now();
        log.info("====> {} available processors. <====", availableProcessors);

        FakeDataBase fakeDataBase = new FakeDataBase();
        Stream<CustomerEntity> customerEntities = fakeDataBase.generateCustomerList().stream();

        if (availableProcessors > 2) {
            log.info("====> More than 2 processors available. Using parallel processing. <====");
            customerEntities.parallel();
        }

        customerEntities
                .map(c -> createReportDTO(c))
                .collect(Collectors.toSet());

        log.info("Operation duration {} sec", Duration.between(startTime, LocalDateTime.now()).toSeconds());

    }

    private static ReportDTO createReportDTO(CustomerEntity customerEntity) {
        BigDecimal totalAmount = customerEntity.getPurchaseTransactions().stream()
                .map(PurchaseTransactionEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        SleepUtils.loadingSimulator(1);
        log.info("Total amount spend {}", totalAmount);
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(customerEntity.getId());
        reportDTO.setFullName(customerEntity.getFullName());
        reportDTO.setTotalAmountSpend(totalAmount);
        return reportDTO;
    }

}
