package com.github.com.ronaldocutrim.Expenses_Control.core.transaction.dto;

import java.util.List;

public record TransactionListOutput(List<TransactionOutput> transactions, Metadata metadata) {

    public record Metadata(String total, int count) {}
}