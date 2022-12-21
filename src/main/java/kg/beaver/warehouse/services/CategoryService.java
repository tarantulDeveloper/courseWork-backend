package kg.beaver.warehouse.services;

import kg.beaver.warehouse.entities.Category;

import java.util.List;

public interface CategoryService {
    boolean deleteById(Integer id);
    Category createCategory(String categoryName);

    List<Category> getAllCategories();
}
