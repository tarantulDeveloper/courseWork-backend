package kg.beaver.warehouse.controllers;

import kg.beaver.warehouse.entities.Material;
import kg.beaver.warehouse.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequiredArgsConstructor
@RequestMapping("/api/material")
public class MaterialController {
    private final MaterialService materialService;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('WORKER') or hasRole('ADMIN')")
    public Material addMaterial(@RequestParam("file") MultipartFile file,
                                @RequestParam("price") double price,
                                @RequestParam("category") String category,
                                @RequestParam("materialName") String materialName,
                                @RequestParam("quantity") int quantity,
                                @RequestParam("size") String size,
                                @RequestParam("description") String description) throws IOException {


        if(file.isEmpty()) {

        }
        return materialService.createMaterial(
                file, price, category, materialName, quantity, size, description
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('WORKER') or hasRole('ADMIN')")
    public boolean deleteMaterialById(@PathVariable("id") Integer id) {
        return materialService.deleteById(id);
    }
}
