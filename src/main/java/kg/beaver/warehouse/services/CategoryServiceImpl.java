package kg.beaver.warehouse.services;

import kg.beaver.warehouse.entities.Category;
import kg.beaver.warehouse.exceptions.RegistrationException;
import kg.beaver.warehouse.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public boolean deleteById(Integer id) {
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Category createCategory(String categoryName) {
        if (categoryRepository.existsByName(categoryName)) {
            throw new RegistrationException("The same category already exist.");
        }
        return categoryRepository.save(new Category(categoryName));
    }
}
