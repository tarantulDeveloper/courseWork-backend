package kg.beaver.warehouse.controllers;

import kg.beaver.warehouse.entities.Category;
import kg.beaver.warehouse.exceptions.RegistrationException;
import kg.beaver.warehouse.repo.CategoryRepository;
import kg.beaver.warehouse.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('WORKER')")
    public Category createCategory(@RequestParam("categoryName") String categoryName) {
        return categoryService.createCategory(categoryName);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('WORKER')")
    public boolean deleteCategory(@PathVariable("id") Integer id) {
        return categoryService.deleteById(id);
    }

}
