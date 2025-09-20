package com.github.com.ronaldocutrim.Expenses_Control.core.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.com.ronaldocutrim.Expenses_Control.core.category.model.CategoryModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
@Builder(builderClassName = "builder", setterPrefix = "with")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private CategoryModel category;
}