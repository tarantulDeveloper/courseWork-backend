package kg.beaver.warehouse.services;

import kg.beaver.warehouse.entities.Category;

public interface CategoryService {
    boolean deleteById(Integer id);
    Category createCategory(String categoryName);
}
