package com.github.com.ronaldocutrim.Expenses_Control.core.category;

import com.github.com.ronaldocutrim.Expenses_Control.core.category.dto.CategoryInput;
import com.github.com.ronaldocutrim.Expenses_Control.core.category.dto.CategoryListOutput;
import com.github.com.ronaldocutrim.Expenses_Control.core.category.dto.CategoryMapper;
import com.github.com.ronaldocutrim.Expenses_Control.core.category.dto.CategoryOutput;
import com.github.com.ronaldocutrim.Expenses_Control.core.category.model.CategoryModel;
import com.github.com.ronaldocutrim.Expenses_Control.core.transaction.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListOutput getAllCategories() {
        List<CategoryModel> categories = categoryService.findAllWithTransactions();
        return CategoryMapper.toListOutput(categories);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryOutput getCategoryById(@PathVariable Long id) {
        CategoryModel category = categoryService.findByIdWithTransactions(id);
        return CategoryMapper.toOutput(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CategoryInput requestDTO) {
        CategoryModel category = CategoryMapper.toModel(requestDTO);
        categoryService.save(category);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable Long id, @RequestBody CategoryInput requestDTO) {
        CategoryModel category = CategoryMapper.toModel(requestDTO);
        categoryService.update(id, category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}