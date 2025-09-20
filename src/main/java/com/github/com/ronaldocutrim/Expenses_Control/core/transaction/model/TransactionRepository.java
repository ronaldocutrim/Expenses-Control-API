package com.github.com.ronaldocutrim.Expenses_Control.core.transaction.model;

import com.github.com.ronaldocutrim.Expenses_Control.core.category.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t JOIN FETCH t.category")
    List<Transaction> findAllWithCategory();

    @Query("SELECT t FROM Transaction t JOIN FETCH t.category WHERE t.id = :id")
    Optional<Transaction> findByIdWithCategory(Long id);

    @Query("SELECT t FROM Transaction t JOIN FETCH t.category WHERE t.category.id = :categoryId")
    List<Transaction> findByCategoryId(Long categoryId);
}