package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import sia.tacocloud.dto.Ingredient;
import sia.tacocloud.dto.Ingredient.Type;
import sia.tacocloud.dto.Taco;
import sia.tacocloud.repositories.IngredientRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@Slf4j
public class DesignTacoController {

  private final IngredientRepository ingredientRepository;

  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  @GetMapping
  public String showDesignForm(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepository.findAll().forEach(i -> ingredients.add(i));

    Type[] types = Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }

    model.addAttribute("design", new Taco());

    return "design";
  }

  @PostMapping
  public String processDesign(@Valid Taco design, Errors errors) {
    if (errors.hasErrors()) {
      return "design";
    }

    // Save the taco design...
    // We'll do this in chapter 3
    log.info("Process design: {}", design);

    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients.stream()
            .filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
  }
}