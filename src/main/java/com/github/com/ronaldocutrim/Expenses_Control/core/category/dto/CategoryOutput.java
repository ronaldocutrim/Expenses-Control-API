package com.github.com.ronaldocutrim.Expenses_Control.core.category.dto;

import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.dto.TransactionOutput;

import java.util.List;

public record CategoryOutput(
        Long id,
        String name,
        String color,
        List<TransactionOutput> transactions,
        Metadata metadata
) {
    public record Metadata(String total, int count) {}
}