package kg.beaver.warehouse.services;

import kg.beaver.warehouse.entities.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    List<Order> getAll();

    void deleteById(Integer id);
}
