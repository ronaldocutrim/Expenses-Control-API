package com.github.com.ronaldocutrim.Expenses_Control.core.category.dto;

import java.util.List;

public record CategoryListOutput(List<CategoryOutput> categories, Metadata metadata) {

    public record Metadata(String total, int count) {}
}