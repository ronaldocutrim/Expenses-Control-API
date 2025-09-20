package com.github.com.ronaldocutrim.Expenses_Control.core.transaction;

import com.github.com.ronaldocutrim.Expenses_Control.contracts.NotFound;
import com.github.com.ronaldocutrim.Expenses_Control.core.category.CategoryService;
import com.github.com.ronaldocutrim.Expenses_Control.core.category.model.CategoryModel;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.dto.TransactionInput;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.model.Transaction;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.model.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryService categoryService;

    public List<Transaction> findAll() {
        return transactionRepository.findAllWithCategory();
    }

    public Transaction findById(Long id) {
        return transactionRepository.findByIdWithCategory(id).orElseThrow(() -> new NotFound("Transaction not found"));
    }

    @Transactional
    public void create(TransactionInput input) {
        CategoryModel category = categoryService.findById(input.category_id());

        Transaction transaction = Transaction.builder()
                .withDescription(input.description())
                .withPrice(input.price())
                .withCategory(category)
                .build();
        transactionRepository.save(transaction);
    }

    @Transactional
    public void update(Long id, TransactionInput input) {
        Transaction existingTransaction = findById(id);
        CategoryModel category = categoryService.findById(input.category_id());

        existingTransaction.setDescription(input.description());
        existingTransaction.setPrice(input.price());
        existingTransaction.setCategory(category);
        transactionRepository.save(existingTransaction);
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
}
