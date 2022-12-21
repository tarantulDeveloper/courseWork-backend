package kg.beaver.warehouse.services;

import kg.beaver.warehouse.entities.Choice;
import kg.beaver.warehouse.repo.ChoiceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChoiceServiceImpl implements ChoiceService{
    private final ChoiceRepo choiceRepo;
    @Override
    public void createChoice(String name) {
        Choice choice = new Choice();
        choice.setName(name);
        choiceRepo.save(choice);
    }

    @Override
    public List<Choice> getAll() {
        return choiceRepo.findAll();
    }

    @Override
    public void deleteAll() {
        choiceRepo.deleteAll();
    }
}
