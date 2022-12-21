package kg.beaver.warehouse.controllers;

import kg.beaver.warehouse.entities.Choice;
import kg.beaver.warehouse.entities.Material;
import kg.beaver.warehouse.services.ChoiceService;
import kg.beaver.warehouse.services.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/common")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequiredArgsConstructor
public class CommonController {
    private final MaterialService materialService;
    private final ChoiceService choiceService;

    @GetMapping("/materials")
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @PostMapping("/choice")
    public void createChoice(@RequestParam String name){
        choiceService.createChoice(name);
    }

    @GetMapping("/choice")
    public List<Choice> getAllChoice() {
        return choiceService.getAll();
    }

    @DeleteMapping("/choice")
    public void deleteChoices() {
        choiceService.deleteAll();
    }

}
