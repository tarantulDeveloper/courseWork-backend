package kg.beaver.warehouse.services;

import kg.beaver.warehouse.entities.Category;
import kg.beaver.warehouse.entities.Material;
import kg.beaver.warehouse.exceptions.RegistrationException;
import kg.beaver.warehouse.exceptions.ResourceNotFoundException;
import kg.beaver.warehouse.repo.CategoryRepository;
import kg.beaver.warehouse.repo.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    @Value("${upload.path}")
    private String uploadPath;
    private final MaterialRepository materialRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Material createMaterial(MultipartFile file, double price, String categoryName,
                                   String materialName, int quantity, String size, String description) throws IOException {
        if (materialRepository.existsByName(materialName)) {
            throw new RegistrationException("The material is already added.");
        }
        if (!categoryRepository.existsByName(categoryName)) {
            throw new ResourceNotFoundException("No such category.");
        }
        //saving image to 'upload' folder
        String fileName = "";
        if(!file.isEmpty()) {
            File uploadDir = new File(uploadPath);
            fileName = file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + fileName));
        } else {
            fileName = "default-img.png";
        }





        Category category= categoryRepository.findByName(categoryName).get();
        Material material = new Material();
        material.setCategory(category);
        material.setName(materialName);
        material.setPrice(price);
        material.setPhoto(uploadPath + "/" + fileName);
        material.setDescription(description);
        material.setQuantity(quantity);
        material.setSize(size);
        return materialRepository.save(material);

    }

    @Override
    public boolean deleteById(Integer id) {
        if(materialRepository.existsById(id)){
            materialRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
