package sia.tacocloud.resources;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import sia.tacocloud.assemblers.IngredientResourceAssembler;
import sia.tacocloud.dao.Taco;

import java.util.Date;
import java.util.List;

@Getter
public class TacoResource extends ResourceSupport {

  private static final IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();

  private final String name;

  private final Date createdAt;

  private final List<IngredientResource> ingredients;

  public TacoResource(Taco taco) {
    this.name = taco.getName();
    this.createdAt = taco.getCreatedAt();
    this.ingredients = ingredientAssembler.toResources(taco.getIngredients());
  }
}
