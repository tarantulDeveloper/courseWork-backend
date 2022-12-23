package kg.beaver.warehouse.controllers;

import kg.beaver.warehouse.entities.Choice;
import kg.beaver.warehouse.entities.Material;
import kg.beaver.warehouse.entities.Order;
import kg.beaver.warehouse.services.ChoiceService;
import kg.beaver.warehouse.services.MaterialService;
import kg.beaver.warehouse.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/api/common")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequiredArgsConstructor
public class CommonController {
    private final MaterialService materialService;
    private final ChoiceService choiceService;
    private final OrderService orderService;

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

    @PostMapping("/order")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/order")
    public List<Order> getAllOrders() {
        return orderService.getAll();
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrderById(@PathVariable("id") Integer id) {
        orderService.deleteById(id);
    }

}
