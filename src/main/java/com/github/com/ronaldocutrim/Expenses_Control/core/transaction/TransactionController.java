package com.github.com.ronaldocutrim.Expenses_Control.core.transaction;

import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.dto.TransactionInput;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.dto.TransactionListOutput;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.dto.TransactionMapper;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.dto.TransactionOutput;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TransactionListOutput getAll() {
        List<Transaction> transactions = transactionService.findAll();
        return TransactionMapper.toListOutput(transactions);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionOutput details(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        return TransactionMapper.toOutput(transaction);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TransactionInput requestDTO) {
        transactionService.create(requestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody TransactionInput requestDTO) {
        transactionService.update(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }
}
