package kg.beaver.warehouse.services;

import kg.beaver.warehouse.entities.Material;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MaterialService {
    Material createMaterial(MultipartFile photo, double price, String categoryName,
                            String materialName, int quantity, String size, String description) throws IOException;

    boolean deleteById(Integer id);

    List<Material> getAllMaterials();
}
