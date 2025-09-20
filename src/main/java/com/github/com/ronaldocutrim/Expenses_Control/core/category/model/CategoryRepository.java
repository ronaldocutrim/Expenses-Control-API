package com.github.com.ronaldocutrim.Expenses_Control.core.category.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    Optional<CategoryModel> findByName(String name);
    boolean existsByName(String name);

    @Query("SELECT c FROM CategoryModel c LEFT JOIN FETCH c.transactions")
    List<CategoryModel> findAllWithTransactions();

    @Query("SELECT c FROM CategoryModel c LEFT JOIN FETCH c.transactions WHERE c.id = :id")
    Optional<CategoryModel> findByIdWithTransactions(@Param("id") Long id);
}