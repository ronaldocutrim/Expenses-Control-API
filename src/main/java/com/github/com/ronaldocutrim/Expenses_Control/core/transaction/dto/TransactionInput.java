package com.github.com.ronaldocutrim.Expenses_Control.core.transaction.dto;

import java.math.BigDecimal;

public record TransactionInput(BigDecimal price, String description, Long category_id) {
}
