package sia.tacocloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.dao.Ingredient;
import sia.tacocloud.dao.Ingredient.Type;
import sia.tacocloud.dao.Order;
import sia.tacocloud.dao.Taco;
import sia.tacocloud.repositories.TacoRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {

  private TacoRepository tacoRepository;

  @Autowired
  EntityLinks entityLinks;

  @Autowired
  public DesignTacoController(TacoRepository tacoRepository) {
    this.tacoRepository = tacoRepository;
  }

  @GetMapping("/recent")
  public Iterable<Taco> recentTacos() {
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());

    List<Taco> tacos = tacoRepository.findAll(page).getContent();
    Resources<Resources<Taco>> recentResources = Resources.wrap(tacos);

    recentResources.add(new Link("http://localhost:8080/design/recent", "recents"));
    return recentResources;
  }

  @GetMapping("/{id")
  public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
    Optional<Taco> tacoOptional = tacoRepository.findById(id);
    if (tacoOptional.isPresent()) {
      return new ResponseEntity<>(tacoOptional.get(), HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoRepository.save(taco);
  }
}