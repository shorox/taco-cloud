package sia.tacocloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.assemblers.TacoResourceAssembler;
import sia.tacocloud.dao.Taco;
import sia.tacocloud.repositories.TacoRepository;
import sia.tacocloud.resources.TacoResource;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
  public Resources<TacoResource> recentTacos() {
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    List<Taco> tacos = tacoRepository.findAll(page).getContent();

    List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
    Resources<TacoResource> recentResources = new Resources<>(tacoResources);
    recentResources.add(linkTo(methodOn(DesignTacoController.class).recentTacos()).withRel("recents"));

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