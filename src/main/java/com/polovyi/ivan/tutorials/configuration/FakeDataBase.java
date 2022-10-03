package com.polovyi.ivan.tutorials.configuration;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import com.polovyi.ivan.tutorials.entity.CustomerEntity;
import com.polovyi.ivan.tutorials.entity.PurchaseTransactionEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class FakeDataBase {

    private Faker faker = new Faker();

    public List<CustomerEntity> generateCustomerList() {
        return IntStream.range(0, 10)
                .mapToObj(i -> CustomerEntity.builder().createdAt(
                                LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 10)))))
                        .fullName(faker.name().fullName())
                        .phoneNumber(faker.phoneNumber().cellPhone())
                        .address(faker.address().fullAddress())
                        .purchaseTransactions(generatePurchaseTransactionList(faker))
                        .build())
                .collect(toList());
    }

    private Set<PurchaseTransactionEntity> generatePurchaseTransactionList(Faker faker) {
        return IntStream.range(0, new Random().nextInt(10))
                .mapToObj(i -> PurchaseTransactionEntity.builder().createdAt(
                                LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 10)))))
                        .amount(new BigDecimal(faker.commerce().price().replaceAll(",", ".")))
                        .paymentType(List.of(CreditCardType.values())
                                .get(new Random().nextInt(CreditCardType.values().length)).toString())
                        .build())
                .collect(Collectors.toSet());

    }
}


