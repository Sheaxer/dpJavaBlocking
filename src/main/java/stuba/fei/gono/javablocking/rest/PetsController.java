package stuba.fei.gono.javablocking.rest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stuba.fei.gono.javablocking.data.PetsRepository;
import stuba.fei.gono.javablocking.pojo.Pets;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetsController {

    private final PetsRepository repository;
    @Autowired
    public PetsController(PetsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Pets> getAllPets()
    {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Pets getById(@PathVariable("id")ObjectId id)
    {
        return repository.findBy_id(id);
    }

    @PutMapping(value = "/{id}")
    public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Pets pets) {
        pets.set_id(id);
        repository.save(pets);
    }

    @PostMapping
    public Pets createPet(@Valid @RequestBody Pets pets) {
        pets.set_id(ObjectId.get());
        repository.save(pets);
        return pets;
    }
}
