package com.github.com.ronaldocutrim.Expenses_Control.core.category.dto;

import com.github.com.ronaldocutrim.Expenses_Control.core.category.model.CategoryModel;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.dto.TransactionMapper;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.dto.TransactionOutput;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.model.Transaction;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public final class CategoryMapper {

    public static CategoryModel toModel(CategoryInput dto) {
        return CategoryModel
                .builder()
                .withColor(dto.color())
                .withName(dto.name())
                .build();
    }

    public static CategorySimpleOutput toSimpleOutput(CategoryModel model) {
        return new CategorySimpleOutput(
                model.getId(),
                model.getName(),
                model.getColor()
        );
    }

    public static CategoryOutput toOutput(CategoryModel model) {
        List<TransactionOutput> transactionOutputs = model.getTransactions().stream()
                .map(TransactionMapper::toOutput)
                .toList();

        BigDecimal total = model.getTransactions().stream()
                .map(Transaction::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setCurrencySymbol("R$");
        DecimalFormat currencyFormat = new DecimalFormat("¤ #,##0.00", symbols);
        String totalFormatted = currencyFormat.format(total);

        CategoryOutput.Metadata metadata = new CategoryOutput.Metadata(totalFormatted, model.getTransactions().size());

        return new CategoryOutput(
                model.getId(),
                model.getName(),
                model.getColor(),
                transactionOutputs,
                metadata
        );
    }


    public static CategoryListOutput toListOutput(List<CategoryModel> categories) {
        List<CategoryOutput> categoryOutputs = categories.stream()
                .map(CategoryMapper::toOutput)
                .toList();

        BigDecimal total = categories.stream()
                .flatMap(category -> category.getTransactions().stream())
                .map(Transaction::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setCurrencySymbol("R$");
        DecimalFormat currencyFormat = new DecimalFormat("¤ #,##0.00", symbols);
        String totalFormatted = currencyFormat.format(total);

        CategoryListOutput.Metadata metadata = new CategoryListOutput.Metadata(totalFormatted, categories.size());

        return new CategoryListOutput(categoryOutputs, metadata);
    }
}