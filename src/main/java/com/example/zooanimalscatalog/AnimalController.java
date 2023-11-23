package com.example.zooanimalscatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable Integer id) {
        return animalRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Animal addAnimal(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    @PutMapping("/{id}")
    public Animal editAnimal(@PathVariable Integer id, @RequestBody Animal updatedAnimal) {
        return animalRepository.findById(id)
                .map(animal -> {
                    updatedAnimal.setId(id);
                    return animalRepository.save(updatedAnimal);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Integer id) {
        animalRepository.deleteById(id);
    }
}
