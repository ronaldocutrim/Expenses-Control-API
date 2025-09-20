package com.github.com.ronaldocutrim.Expenses_Control.core.transaction.dto;

import com.github.com.ronaldocutrim.Expenses_Control.core.category.dto.CategorySimpleOutput;

public record TransactionOutput(Long id, String description, CategorySimpleOutput category, String price) {
}
