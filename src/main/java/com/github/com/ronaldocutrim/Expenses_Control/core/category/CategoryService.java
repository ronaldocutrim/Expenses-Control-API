package com.github.com.ronaldocutrim.Expenses_Control.core.category;

import com.github.com.ronaldocutrim.Expenses_Control.contracts.NotFound;
import com.github.com.ronaldocutrim.Expenses_Control.core.category.model.CategoryModel;
import com.github.com.ronaldocutrim.Expenses_Control.core.category.model.CategoryRepository;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.model.Transaction;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.model.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    private static final Pattern COLOR_PATTERN = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

    public List<CategoryModel> findAll() {
        return categoryRepository.findAll();
    }

    public CategoryModel findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFound("Category not found"));
    }

    public void save(CategoryModel category) {
         categoryRepository.save(category);
    }

    public void update(Long id, CategoryModel category) {
        CategoryModel existingCategory = findById(id);

        existingCategory.setName(category.getName());
        existingCategory.setColor(category.getColor());

        categoryRepository.save(existingCategory);
    }

    public void deleteById(Long id) {
        CategoryModel category = findById(id);
        categoryRepository.delete(category);
    }

    public List<Transaction> findTransactionsByCategoryId(Long categoryId) {
        return transactionRepository.findByCategoryId(categoryId);
    }

    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<CategoryModel> findAllWithTransactions() {
        return categoryRepository.findAllWithTransactions();
    }

    public CategoryModel findByIdWithTransactions(Long id) {
        return categoryRepository.findByIdWithTransactions(id)
                .orElseThrow(() -> new NotFound("Category not found"));
    }
}