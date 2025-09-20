package com.github.com.ronaldocutrim.Expenses_Control.core.transaction.dto;

import com.github.com.ronaldocutrim.Expenses_Control.core.category.dto.CategoryMapper;
import com.github.com.ronaldocutrim.Expenses_Control.core.category.model.CategoryModel;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.model.Transaction;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public final class TransactionMapper {

    public static Transaction toModel(TransactionInput dto, CategoryModel category) {
        return  Transaction
                .builder()
                .withDescription(dto.description())
                .withPrice(dto.price())
                .withCategory(category)
                .build();
    }

    public static TransactionOutput toOutput(Transaction model) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setCurrencySymbol("R$");
        DecimalFormat currencyFormat = new DecimalFormat("¤ #,##0.00", symbols);

        return new TransactionOutput(
                model.getId(),
                model.getDescription(),
                CategoryMapper.toSimpleOutput(model.getCategory()),
                currencyFormat.format(model.getPrice())
        );
    }

    public static TransactionListOutput toListOutput(List<Transaction> transactions) {
        List<TransactionOutput> transactionOutputs = transactions.stream()
                .map(TransactionMapper::toOutput)
                .toList();

        BigDecimal total = transactions.stream()
                .map(Transaction::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setCurrencySymbol("R$");
        DecimalFormat currencyFormat = new DecimalFormat("¤ #,##0.00", symbols);
        String totalFormatted = currencyFormat.format(total);

        TransactionListOutput.Metadata metadata = new TransactionListOutput.Metadata(totalFormatted, transactions.size());

        return new TransactionListOutput(transactionOutputs, metadata);
    }
}
