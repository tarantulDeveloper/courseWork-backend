package kg.beaver.warehouse.services;

import kg.beaver.warehouse.entities.Choice;

import java.util.List;

public interface ChoiceService {
    void createChoice(String name);

    List<Choice> getAll();

    void deleteAll();
}
